package ru.datadv.sensors.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.datadv.sensors.data.SensorState;

@Service
public class SensorsService implements ISensorsService {

	private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Double>> currentSensorsStates;
	
	public SensorsService() {
		currentSensorsStates = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Double>>();
	}
	
	@Autowired
	private SensorsRepository repository;
	
	@Override
	public void save(SensorState sensorState) {
		
		repository.save(sensorState);
	}

	@Override
	public List<SensorState> getSensorStates(int sensorId, LocalDateTime from, LocalDateTime to) {
		return repository.getSensorStates(sensorId, from, to);
	}
	
	
}
