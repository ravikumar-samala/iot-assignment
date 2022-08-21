package com.ravi.iot.service.impl;

import com.ravi.iot.model.Thermostat;
import com.ravi.iot.repository.ThermostatRepository;
import com.ravi.iot.service.ThermostatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThermostatServiceImpl implements ThermostatService {

    @Autowired
    private ThermostatRepository thermostatRepository;

    @Override
    public void save(Thermostat thermostat) {
     thermostatRepository.save(thermostat);
    }
}
