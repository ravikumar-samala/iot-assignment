package com.ravi.iot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("thermostats")
public class Thermostat implements Comparable<Thermostat>{
    @Id
    private String id;

    private String deviceId;
    private int value;
    private String time;

    public Thermostat(String id, String deviceId, int value, String time) {
        super();
        this.id = id;
        this.deviceId = deviceId;
        this.value = value;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getValue() {
        return value;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return value+"";
    }

    @Override
    public int compareTo(Thermostat o) {
        if(this.value < o.getValue())
            return -1;
        else if(this.value > o.getValue())
            return 1;
        else return 0;

    }
}
