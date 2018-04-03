package ru.datadv.sensors.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import ru.datadv.sensors.data.ObjectStatistics;
import ru.datadv.sensors.data.SensorState;
import ru.datadv.sensors.data.SensorStatistics;
import ru.datadv.sensors.persistence.SensorsRepository;

@Service
public class SensorsService implements ISensorsService {

	private ConcurrentHashMap<Integer, SensorStatistics> currentSensorsStates;

	public SensorsService() {
		currentSensorsStates = new ConcurrentHashMap<Integer, SensorStatistics>();
	}

	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired
	private SensorsRepository repository;

	@Override
	public void save(SensorState sensorState) throws InterruptedException, ExecutionException {
		taskExecutor.execute(() -> repository.save(sensorState));

		SensorStatistics objectSensorsStates = currentSensorsStates.get(sensorState.getObjectId());
		if (objectSensorsStates == null) {
			currentSensorsStates.putIfAbsent(sensorState.getObjectId(), new SensorStatistics());
			objectSensorsStates = currentSensorsStates.get(sensorState.getObjectId());
		}
	
		objectSensorsStates.UpdateSensorState(sensorState);
	}

	@Override
	public List<SensorState> getSensorStates(int sensorId, LocalDateTime from, LocalDateTime to) {
		return repository.getSensorStates(sensorId, from, to);
	}

	@Override
	public List<SensorState> getSensorStates(int objectId) {
		SensorStatistics state = currentSensorsStates.get(objectId);

		if (state != null) {
			return state.GetStates();
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<ObjectStatistics> getAverageSensorsValue() {
		List<ObjectStatistics> result = new ArrayList<ObjectStatistics>(currentSensorsStates.size());

		currentSensorsStates.forEachEntry(0, (entry) -> {
			result.add(new ObjectStatistics(entry.getKey(), entry.getValue().getAverage()));
		});

		return result;
	}

}
