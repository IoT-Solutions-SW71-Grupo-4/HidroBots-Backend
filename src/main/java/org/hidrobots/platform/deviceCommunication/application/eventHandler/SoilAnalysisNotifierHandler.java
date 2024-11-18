package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.deviceCommunication.domain.model.commands.AnalyzeSoilCommand;
import org.hidrobots.platform.deviceCommunication.domain.services.SoilAnalysisNotifier;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class SoilAnalysisNotifierHandler implements SoilAnalysisNotifier {
    @Autowired
    private MqttClient mqttClient;

    private final DeviceRepository deviceRepository;

    @Autowired
    private Gson gson;

    private final String SOIL_ANALYSIS_TOPIC = "hidrobots/soil_analysis/requests";

    public SoilAnalysisNotifierHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public String sendAnalysisRequestToDevice(AnalyzeSoilCommand command) {

        var existingDevice = deviceRepository.findByDeviceCode(command.getDeviceCode())
                .orElseThrow(() -> new IllegalArgumentException("Device with code " + command.getDeviceCode() + " doesn't exist!!"));

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
