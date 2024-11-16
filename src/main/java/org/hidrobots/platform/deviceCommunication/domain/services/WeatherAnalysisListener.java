package org.hidrobots.platform.deviceCommunication.domain.services;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface WeatherAnalysisListener {
    void handleWeatherAnalysisResponseFromDevice(MqttMessage message);
}
