package ru.datadv.sensors.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.datadv.sensors.data.ObjectStatistics;
import ru.datadv.sensors.data.SensorState;

public interface ISensorsService {
	
	public void save(SensorState sensorState) throws InterruptedException, ExecutionException;
	
	public List<SensorState> getSensorStates(int sensorId, LocalDateTime from, LocalDateTime to);
	
	public List<SensorState> getSensorStates(int objectId);
	
	public List<ObjectStatistics> getAverageSensorsValue();
}
