package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.deviceCommunication.domain.services.WeatherAnalysisListener;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportWithDeviceCodeCommand;
import org.hidrobots.platform.report.domain.service.WeatherReportCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherAnalysisListenerHandler implements WeatherAnalysisListener {

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final WeatherReportCommandService weatherReportCommandService;

    public WeatherAnalysisListenerHandler(ObjectMapper objectMapper, WeatherReportCommandService weatherReportCommandService) {
        this.objectMapper = objectMapper;
        this.weatherReportCommandService = weatherReportCommandService;
    }

    @Override
    public void handleWeatherAnalysisResponseFromDevice(MqttMessage message) {
        System.out.println("Handling weather analysis response from device");
        try {
            var response = new String(message.getPayload());
            System.out.println(response);
            var command = objectMapper.readValue(response, CreateWeatherReportWithDeviceCodeCommand.class);
            weatherReportCommandService.createWeatherReport(command);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
