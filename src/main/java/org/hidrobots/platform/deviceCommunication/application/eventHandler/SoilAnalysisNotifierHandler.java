package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.deviceCommunication.domain.model.commands.AnalyzeSoilCommand;
import org.hidrobots.platform.deviceCommunication.domain.services.SoilAnalysisNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoilAnalysisNotifierHandler implements SoilAnalysisNotifier {
    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private Gson gson;

    private final String SOIL_ANALYSIS_TOPIC = "hidrobots/soil_analysis/requests";

    @Override
    public String sendAnalysisRequestToDevice(AnalyzeSoilCommand command) {
        try {
            MqttMessage mqttMessage = new MqttMessage(gson.toJson(command).getBytes());
            mqttMessage.setQos(2);
            mqttClient.publish(SOIL_ANALYSIS_TOPIC, mqttMessage);
            return "Message published to topic";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to publish message";
        }
    }
}
