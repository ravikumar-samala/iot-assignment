package com.ravi.iot.repository;



import com.ravi.iot.model.Thermostat;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

import java.util.List;

public interface ThermostatRepository extends MongoRepository<Thermostat, String> {

    Thermostat findFirstByDeviceId(String deviceId);

    @Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $value }}}" })
    double max();


    @Query(value="{'time': {$gte: ?0, $lte: ?1 }}")
    List<Thermostat> findThermostatMean(LocalDateTime start,LocalDateTime end);
}
