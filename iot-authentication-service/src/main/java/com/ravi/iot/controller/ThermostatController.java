package com.ravi.iot.controller;
import com.ravi.iot.model.Thermostat;
import com.ravi.iot.repository.ThermostatRepository;
import com.ravi.iot.service.ThermostatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/iot-demo")
public class ThermostatController {

    @Autowired
    private ThermostatService thermostatService;



    @RequestMapping(value = "/thermo-mean", method = RequestMethod.GET)
    public double getThermoStatMean(@RequestParam(value ="startTime", required = true)String startTime,
                                    @RequestParam(value = "endTime", required = true) String endTime) {
        double meanValue = thermostatService.findThermostatMean(startTime,endTime);

        return meanValue;
    }

    @RequestMapping(value = "/thermo-min", method = RequestMethod.GET)
    public double getThermoStatMin(@RequestParam(value ="date", required = true)String date) {

        return thermostatService.getThermoStatMin(date);

    }

    @RequestMapping(value = "/thermo-max", method = RequestMethod.GET)
    public double getThermoStatMax(@RequestParam(value ="date", required = true)String date) {

        return thermostatService.getThermoStatMax(date);

    }


}