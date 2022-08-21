package com.iot.sensor.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TempMessage {

    private String sensorId;
    //private double temp;
   // private String deviceId;
    private double value;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public TempMessage() {}

    public TempMessage(String sensorId, double value, LocalDateTime time) {
        this.sensorId = sensorId;
        this.value = value;
        this.time = time;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TempMessage{" +
                "sensorId='" + sensorId + '\'' +
                ", value=" + value +
                '}';
    }
}
