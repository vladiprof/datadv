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

	@Query(value = "select * from sensors_states a INNER JOIN ("
			+ "select object_id, sensor_id, MAX(timestamp) AS timestamp from sensors_states group by (object_id, sensor_id)) "
			+ "b ON a.object_id = b.object_id AND a.sensor_id = b.sensor_id AND a.timestamp = b.timestamp", nativeQuery = true)
	public List<SensorState> getLastSensorsStates();

}
