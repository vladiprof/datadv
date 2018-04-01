package ru.datadv.sensors.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.datadv.sensors.data.SensorState;
import ru.datadv.sensors.persistence.ISensorsService;

@RestController
public class Sensors {

	@Autowired
	ISensorsService sensorsService;

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public void store(@RequestBody SensorState sensorState) {
		sensorsService.save(sensorState);
	}

	@RequestMapping(value = "/get")
	public List<SensorState> get(@RequestParam int sensorId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
		return sensorsService.getSensorStates(sensorId, from, to);
	}

}
