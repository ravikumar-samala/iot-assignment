package com.ravi.iot.repository;



import com.ravi.iot.model.Thermostat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThermostatRepository extends MongoRepository<Thermostat, String> {

}
