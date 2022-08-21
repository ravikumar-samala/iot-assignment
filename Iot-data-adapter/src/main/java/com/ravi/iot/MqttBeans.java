package com.ravi.iot;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ravi.iot.model.CarFuel;
import com.ravi.iot.model.Heartrate;
import com.ravi.iot.model.Thermostat;
import com.ravi.iot.repository.ThermostatRepository;

import com.ravi.iot.service.CarFuelService;
import com.ravi.iot.service.HeartRateService;
import com.ravi.iot.service.ThermostatService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.json.simple.JSONValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class MqttBeans {

	@Autowired
	private ThermostatService thermostatService;

	@Autowired
	private HeartRateService heartRateService;

	@Autowired
	private CarFuelService carFuelService;
	
	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();

		options.setServerURIs(new String[] { "tcp://localhost:1883" });
		options.setUserName("admin");
		String pass = "12345678";
		options.setPassword(pass.toCharArray());
		options.setCleanSession(true);

		factory.setConnectionOptions(options);

		return factory;
	}
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn",
				mqttClientFactory(), "#");

		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}
	
	
	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
				if(topic.equals("thermostats")) {
					System.out.println("This is the topic");

					String json = message.getPayload().toString();

					Thermostat thermostat = (Thermostat) getMessage(json,"thermostat");
					thermostatService.save(thermostat);

					System.out.println("payload" + message.getPayload());
				}
				if(topic.equals("hearttopic")){

					System.out.println("heart topic");
					String json = message.getPayload().toString();

					Heartrate heartRate1 = (Heartrate)getMessage(json,"heart");
					heartRateService.save(heartRate1);

					System.out.println("payload" + message.getPayload());
				}
				if(topic.equals("carfuel")){

					System.out.println("car fuel topic");
					String json = message.getPayload().toString();

					CarFuel carFuel = (CarFuel) getMessage(json,"carfuel");
					carFuelService.save(carFuel);

					System.out.println("payload" + message.getPayload());
				}
			}

		};
	}
	
	
	@Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
	@Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        //clientId is generated using a random number
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }

    private com.ravi.iot.model.Message getMessage(String json, String type){

		Object obj = JSONValue.parse(json);
		JSONObject jsonObject = (JSONObject) obj;
		System.out.println("josn is" + json);
		String id = (String) jsonObject.get("id");
		String sensorId = (String) jsonObject.get("sensorId");
		Double value = (Double) jsonObject.get("value");
		String time = (String) jsonObject.get("time");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
		if(type.equals("heart")) {
			return new Heartrate(id, sensorId, value, dateTime);
		}
		if(type.equals("thermostat")) {
			return new Thermostat(id, sensorId, value, dateTime);
		}
		if(type.equals("carfuel")) {
			return new CarFuel(id, sensorId, value, dateTime);
		}
		return null;
	}

}
