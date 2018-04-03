package ru.datadv.sensors.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class SensorStatistics {

	private double values_sum;
	private HashMap<Integer, SensorState> currentSensorsStates;

	private StampedLock stateLock;

	public void UpdateSensorState(SensorState newState) {
		long stamp = stateLock.writeLock();

		try {
			SensorState oldState = currentSensorsStates.get(newState.getSensorId());

			if (oldState != null) {
				values_sum -= oldState.getValue();
			}
			values_sum += newState.getValue();

			currentSensorsStates.put(newState.getSensorId(), newState);
		} finally {
			stateLock.unlockWrite(stamp);
		}
	}

	public double getAverage() {
		long stamp = stateLock.readLock();

		try {
			if (currentSensorsStates.size() != 0) {
				return values_sum / currentSensorsStates.size();
			} else {
				return 0.0;
			}
		} finally {
			stateLock.unlockRead(stamp);
		}
	}

	public List<SensorState> GetStates() {
		long stamp = stateLock.readLock();

		try {
			return new ArrayList<SensorState>(currentSensorsStates.values());
		} finally {
			stateLock.unlockRead(stamp);
		}
	}

}
