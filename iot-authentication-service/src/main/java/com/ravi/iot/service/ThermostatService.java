package com.ravi.iot.service;


import com.ravi.iot.model.Thermostat;

import java.time.LocalDateTime;
import java.util.List;

public interface ThermostatService {

    double getThermoStatMax(String date);

    double findThermostatMean(String startTime, String endTime);

    double getThermoStatMin(String date);
}
