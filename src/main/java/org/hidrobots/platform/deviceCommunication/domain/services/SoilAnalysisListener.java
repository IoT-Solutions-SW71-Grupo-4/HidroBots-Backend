package org.hidrobots.platform.deviceCommunication.domain.services;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface SoilAnalysisListener {
    void handleSoilAnalysisResponseFromDevice(MqttMessage message);
}
