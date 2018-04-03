package ru.datadv.sensors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveSensorsStatesException extends RuntimeException {

	private static final long serialVersionUID = 6391962944881969800L;

}
