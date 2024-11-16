package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.deviceCommunication.domain.services.WeatherAnalysisListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherAnalysisListenerHandler implements WeatherAnalysisListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleWeatherAnalysisResponseFromDevice(MqttMessage message) {
        var response = new String(message.getPayload());

        System.out.println(response);

        // try {
//
        //     var response = new String(message.getPayload());
        //
        //     System.out.println(response);
        //
        //     //var responseObj = objectMapper.readValue(response, WeatherAnalysisResponse.class);
//
        //     //responseObj.setAnalysisDateTime(LocalDateTime.now());
//
        //     // El responseObj es un objeto q se peude guardar ya en la base de datos
        // } catch (JsonProcessingException e) {
        //     e.printStackTrace();
        // }
    }
}
