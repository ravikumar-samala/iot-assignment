package com.ravi.iot.service.impl;

import com.ravi.iot.model.Thermostat;
import com.ravi.iot.repository.ThermostatRepository;
import com.ravi.iot.service.ThermostatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThermostatServiceImpl implements ThermostatService {

    @Autowired
    private ThermostatRepository thermostatRepository;


    @Override
    public double findThermostatMean(String startTime, String endTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
        List<Thermostat> thermostats = thermostatRepository.findThermostatMean(startDateTime,endDateTime);
        return thermostats.stream().map(x -> x.getValue()).reduce(0,(a,b) -> a+b);

    }

    @Override
    public double getThermoStatMax(String date) {
        return getMedianRange(date).stream().findFirst().map(Thermostat::getValue).orElse(0);
    }

    @Override
    public double getThermoStatMin(String date) {
        return getMedianRange(date).stream().findFirst().map(Thermostat::getValue).orElse(0);

    }

    private List<Thermostat> getMedianRange(String date){

        LocalDate startDate = LocalDate.parse(date);
        LocalTime startTime = LocalTime.parse("00:00:00");
        LocalDateTime startDateTime = LocalDateTime.of(startDate,startTime);


        LocalDate endDate = LocalDate.parse(date);
        LocalTime endTime = LocalTime.parse("23:59:00");

        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

        List<Thermostat> thermostats = thermostatRepository.findThermostatMean(startDateTime,endDateTime);
        Collections.sort(thermostats);
        return thermostats;

    }
}
