package com.ravi.iot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cars")
public class CarMilage {
    @Id
    private String id;

    private String deviceId;
    private int value;
    private String time;

    public CarMilage(String id, String deviceId, int value, String time) {
        super();
        this.id = id;
        this.deviceId = deviceId;
        this.value = value;
        this.time = time;
    }

    @Override
    public String toString() {
        return value+"";
    }
}
