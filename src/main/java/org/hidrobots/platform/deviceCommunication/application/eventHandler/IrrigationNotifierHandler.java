package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.hidrobots.platform.deviceCommunication.domain.model.commands.IrrigationByCropIdCommand;
import org.hidrobots.platform.deviceCommunication.domain.model.commands.IrrigationCommand;
import org.hidrobots.platform.deviceCommunication.domain.services.IrrigationNotifier;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IrrigationNotifierHandler implements IrrigationNotifier {
    @Autowired
    private MqttClient mqttClient;

    private final DeviceRepository deviceRepository;
    private final CropRepository cropRepository;

    @Autowired
    private Gson gson;

    private final String IRRIGATION_TOPIC = "hidrobots/irrigation/requests";

    public IrrigationNotifierHandler(DeviceRepository deviceRepository, CropRepository cropRepository) {
        this.deviceRepository = deviceRepository;
        this.cropRepository = cropRepository;
    }
    @Override
    public String notifyIrrigationSystem(IrrigationCommand command) {

        var existingDevice = deviceRepository.findByDeviceCode(command.getDeviceCode())
                .orElseThrow(() -> new IllegalArgumentException("Device with code " + command.getDeviceCode() + " doesn't exist!!"));

        try {
            MqttMessage mqttMessage = new MqttMessage(gson.toJson(command).getBytes());
            mqttMessage.setQos(2);
            mqttClient.publish(IRRIGATION_TOPIC, mqttMessage);
            return "Irrigation message published to topic";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to publish message";
        }
    }

    @Override
    public String notifyIrrigationSystemForCrop(IrrigationByCropIdCommand command) {
        var crop = cropRepository.findById(command.getCropId())
                .orElseThrow(() -> new IllegalArgumentException("Crop with id " + command.getCropId() + " doesn't exist!!"));

        var devices = deviceRepository.findByCropId(command.getCropId());

        if (devices.isEmpty()) {
            return "No devices found for crop";
        }

        devices.forEach(device -> {
            var irrigationCommand = new IrrigationCommand(device.getDeviceCode(), command.isIrrigationStatus());
            notifyIrrigationSystem(irrigationCommand);
        });

        return "Irrigation message published to all devices";
    }
}
