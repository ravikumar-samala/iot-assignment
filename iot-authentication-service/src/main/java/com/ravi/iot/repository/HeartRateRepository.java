package com.ravi.iot.repository;



import com.ravi.iot.model.Heartrate;
import com.ravi.iot.model.Thermostat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeartRateRepository extends MongoRepository<Heartrate, String> {

    Thermostat findFirstByDeviceId(String deviceId);

}
