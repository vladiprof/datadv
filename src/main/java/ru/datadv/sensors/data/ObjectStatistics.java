package ru.datadv.sensors.data;

public class ObjectStatistics {

	private int objectId;
	
	private double average;
	
	public ObjectStatistics(Integer objectId, double average) {
		this.objectId = objectId;
		this.average = average;
	}

	public int getObjectId() {
		return objectId;
	}

	public double getAverage() {
		return average;
	}

}
