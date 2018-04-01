package ru.datadv.sensors.data;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="sensors_states", indexes = {@Index(name="date_sensor", columnList= "timestamp,sensor_id")})
public class SensorState {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "sensor_id")
	private int sensorId;
	
	@Column(name = "object_id")
	private int objectId;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	@Column(name = "value")
	private double value;
	
	public SensorState() {}
	
	public SensorState(Long id, int sensorId, int objectId, LocalDateTime timestamp, double value) {
		super();
		this.id = id;
		this.sensorId = sensorId;
		this.objectId = objectId;
		this.timestamp = timestamp;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "SensorState [id=" + id + ", sensorId=" + sensorId + ", objectId=" + objectId + ", timestamp="
				+ timestamp + ", value=" + value + "]";
	}	
}
