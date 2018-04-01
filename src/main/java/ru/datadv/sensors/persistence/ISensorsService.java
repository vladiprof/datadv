package ru.datadv.sensors.persistence;

import java.time.LocalDateTime;
import java.util.List;

import ru.datadv.sensors.data.SensorState;

public interface ISensorsService {
	
	public void save(SensorState sensorState);
	
	public List<SensorState> getSensorStates(int sensorId, LocalDateTime from, LocalDateTime to);
}
