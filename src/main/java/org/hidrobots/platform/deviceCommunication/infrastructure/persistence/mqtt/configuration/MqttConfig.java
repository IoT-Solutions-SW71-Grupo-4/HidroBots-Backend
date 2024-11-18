package org.hidrobots.platform.deviceCommunication.infrastructure.persistence.mqtt.configuration;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.hidrobots.platform.deviceCommunication.domain.services.SoilAnalysisListener;
import org.hidrobots.platform.deviceCommunication.domain.services.WeatherAnalysisListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {
    private static final String MQTT_BROKER_URL = "ssl://5a70dde393074cb8995275a580119679.s1.eu.hivemq.cloud:8883";
    private static final String CLIENT_ID = "clientId_hidrobots_backend";
    private static final String USERNAME = "hidrobots";
    private static final String PASSWORD = "Hidrob0ts";

    private static final String WEATHER_ANALYSIS_TOPIC = "hidrobots/weather_analysis/data";
    private static final String SOIL_ANALYSIS_TOPIC = "hidrobots/soil_analysis/data";

    private static final String[] SUBSCRIBE_TOPICS = {
            WEATHER_ANALYSIS_TOPIC,
            SOIL_ANALYSIS_TOPIC
    };

    private final WeatherAnalysisListener weatherAnalysisListener;
    private final SoilAnalysisListener soilAnalysisListener;

    public MqttConfig(WeatherAnalysisListener weatherAnalysisListener, SoilAnalysisListener soilAnalysisListener) {
        this.weatherAnalysisListener = weatherAnalysisListener;
        this.soilAnalysisListener = soilAnalysisListener;
    }

    @Bean
    public MqttClient mqttClient() throws MqttException {
        MqttClient client = new MqttClient(MQTT_BROKER_URL, CLIENT_ID, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Connection lost: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println("Message received on topic: " + topic + ". Content: " + new String(message.getPayload()));

                switch (topic) {
                    case WEATHER_ANALYSIS_TOPIC:
                        System.out.println("Handling weather analysis response");
                        weatherAnalysisListener.handleWeatherAnalysisResponseFromDevice(message);
                        break;
                    case SOIL_ANALYSIS_TOPIC:
                        System.out.println("Handling soil analysis response");
                        soilAnalysisListener.handleSoilAnalysisResponseFromDevice(message);
                        break;
                    default:
                        System.out.println("Unrecognized topic: " + topic);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // Esta función es opcional para suscripciones
            }
        });

        client.connect(options);
        for (String topic : SUBSCRIBE_TOPICS) {
            client.subscribe(topic);
        }
        return client;
    }
}
