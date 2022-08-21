package com.ravi.iot.repository;



import com.ravi.iot.model.CarMilage;
import com.ravi.iot.model.Thermostat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarMilageRepository extends MongoRepository<CarMilage, String> {

    Thermostat findFirstByDeviceId(String deviceId);

}
