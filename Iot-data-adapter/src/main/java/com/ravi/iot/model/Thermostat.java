package com.ravi.iot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("thermostats")
public class Thermostat implements Message {
    @Id
    private String id;

    private String sensorId;
    private double value;
    private LocalDateTime time;

    public Thermostat(String id, String sensorId, double value, LocalDateTime time) {
        super();
        this.id = id;
        this.sensorId = sensorId;
        this.value = value;
        this.time = time;
    }

    @Override
    public String toString() {
        return "DeviceId"+sensorId;
    }
}
