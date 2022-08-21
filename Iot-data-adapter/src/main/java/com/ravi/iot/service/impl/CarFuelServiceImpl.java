package com.ravi.iot.service.impl;

import com.ravi.iot.model.CarFuel;
import com.ravi.iot.model.Heartrate;
import com.ravi.iot.repository.CarFuelRepository;
import com.ravi.iot.repository.HeartRateRepository;
import com.ravi.iot.service.CarFuelService;
import com.ravi.iot.service.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarFuelServiceImpl implements CarFuelService {

    @Autowired
    private CarFuelRepository carFuelRepository;

    @Override
    public void save(CarFuel carFuel) {

        carFuelRepository.save(carFuel);
    }
}
