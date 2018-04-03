package ru.datadv.sensors.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.datadv.sensors.data.ObjectStatistics;
import ru.datadv.sensors.data.SensorState;
import ru.datadv.sensors.service.ISensorsService;

@RestController
public class Sensors {

	@Autowired
	ISensorsService sensorsService;

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public void store(@RequestBody SensorState sensorState) {
		try {
			sensorsService.save(sensorState);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new SaveSensorsStatesException();
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new SaveSensorsStatesException();
		}
	}

	@RequestMapping(value = "/get_sensors_states")
	public List<SensorState> get(@RequestParam int sensorId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
		return sensorsService.getSensorStates(sensorId, from, to);
	}

	@RequestMapping(value = "/get_average")
	public List<ObjectStatistics> get() {
		return sensorsService.getAverageSensorsValue();
	}
	
	@RequestMapping(value = "/get_object_stat")
	public List<SensorState> get(@RequestParam int objectId) {
		return sensorsService.getSensorStates(objectId);
	}
}
