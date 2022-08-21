package com.ravi.iot.service.impl;

import com.ravi.iot.model.Heartrate;
import com.ravi.iot.repository.HeartRateRepository;
import com.ravi.iot.service.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartRateServiceImpl implements HeartRateService {

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Override
    public void save(Heartrate heartrate) {

        heartRateRepository.save(heartrate);
    }
}
