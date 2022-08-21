package com.ravi.iot;

import com.ravi.iot.model.Thermostat;
import com.ravi.iot.repository.ThermostatRepository;
import com.ravi.iot.service.ThermostatService;
import com.ravi.iot.service.impl.ThermostatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ThermostatServiceMockTest {

    @Mock
    private ThermostatRepository thermostatRepository;

    @InjectMocks
    private ThermostatService thermostatService = new ThermostatServiceImpl();

    @BeforeEach
    void setMockOuput(){
        List<Thermostat> thermostatList = new ArrayList<>();
        Thermostat thermostat = new Thermostat("1","device1",30,"2020-02-02");
        thermostatList.add(thermostat);
        Mockito.when(thermostatRepository.findThermostatMean(Mockito.any(),Mockito.any())).thenReturn(thermostatList);
       // when(thermostatRepository.findThermostatMean(LocalDateTime.now(),LocalDateTime.now())).thenReturn(thermostatList);
    }
}
