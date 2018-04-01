package ru.datadv.sensors.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.datadv.sensors.data.SensorState;

public interface SensorsRepository extends JpaRepository<SensorState, Long>, CrudRepository<SensorState, Long> {

	@Query("SELECT state From SensorState state WHERE state.sensorId = :sensorId AND state.timestamp BETWEEN :from AND :to")
	public List<SensorState> getSensorStates(@Param("sensorId") int sensorId, @Param("from") LocalDateTime from,
			@Param("to") LocalDateTime to);


}