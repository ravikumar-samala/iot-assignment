package com.ravi.iot.repository;



import com.ravi.iot.model.CarFuel;
import com.ravi.iot.model.Thermostat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarFuelRepository extends MongoRepository<CarFuel, String> {

}
