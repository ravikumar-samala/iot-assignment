package com.ravi.iot;

import com.ravi.iot.model.Thermostat;
import com.ravi.iot.repository.ThermostatRepository;
import com.ravi.iot.service.ThermostatService;
import com.ravi.iot.service.impl.ThermostatServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ThermostatServiceTest {

  /*  @Autowired
    private ThermostatService thermostatService;*/

    /*@Autowired
    private ThermostatRepository thermostatRepository;*/

    @Mock
    private ThermostatRepository thermostatRepository;

    @InjectMocks
    private ThermostatService thermostatService = new ThermostatServiceImpl();

    @Test
    public void testMean(){
        List<Thermostat> list = new ArrayList<>();
        List<Thermostat> thermostatList = new ArrayList<>();
        Thermostat thermostat = new Thermostat("1","device1",30,"2020-02-02");
        thermostatList.add(thermostat);
         Mockito.when(thermostatRepository.findThermostatMean(Mockito.any(),Mockito.any())).thenReturn(thermostatList);
       // when(thermostatRepository.findThermostatMean(LocalDateTime.now(),LocalDateTime.now())).thenReturn(thermostatList);
        //List<Thermostat> actual = thermostatService.findThermostatMean(LocalDateTime.now(),LocalDateTime.now());
        Double actual = thermostatService.getThermoStatMin("2020-02-02");
        assertEquals(30,actual);
    }
}
