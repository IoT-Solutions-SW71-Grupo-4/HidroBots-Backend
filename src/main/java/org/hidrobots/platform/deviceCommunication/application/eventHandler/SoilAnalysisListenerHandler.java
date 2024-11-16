package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.deviceCommunication.domain.services.SoilAnalysisListener;
import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportWithDeviceCodeCommand;
import org.hidrobots.platform.report.domain.service.SoilReportCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoilAnalysisListenerHandler implements SoilAnalysisListener {
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final SoilReportCommandService soilReportCommandService;

    public SoilAnalysisListenerHandler(ObjectMapper objectMapper, SoilReportCommandService soilReportCommandService) {
        this.objectMapper = objectMapper;
        this.soilReportCommandService = soilReportCommandService;
    }

    @Override
    public void handleSoilAnalysisResponseFromDevice(MqttMessage message) {

        try {
            var response = new String(message.getPayload());
            System.out.println(response);
            var command = objectMapper.readValue(response, CreateSoilReportWithDeviceCodeCommand.class);
            soilReportCommandService.createSoilReport(command);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
