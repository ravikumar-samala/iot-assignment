package com.iot.sensor.temp;


import com.iot.sensor.temp.model.TempMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mimics a temperature sensor that records temperature data every 1 second.
 */
@Component
public class Sensor implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Sensor.class);

    @Autowired
    @Qualifier("mqttClientId")
    private String mqttClientId;

    @Autowired
    private IntegrationFlow mqttOutboundFlow;

    private Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Starting Sensor: {}", mqttClientId);

        Flux.interval(Duration.ofSeconds(1))
                .map(tick -> ThreadLocalRandom.current().nextDouble(50.0, 80.0))
                .subscribe(value -> {
                    LOG.info("Temp: " + value);
                    mqttOutboundFlow.getInputChannel().send(new Message<String>() {
                        @Override
                        public String getPayload() {
                            try {
                                return mapper.toJson(new TempMessage(mqttClientId, value, LocalDateTime.now()));
                            } catch (Exception e) {
                                throw new RuntimeException("Exception occurred building mqtt message", e);
                            }
                        }

                        @Override
                        public MessageHeaders getHeaders() {
                            return new MessageHeaders(Collections.EMPTY_MAP);
                        }
                    });
                });

        Thread.currentThread().join();
    }
}
