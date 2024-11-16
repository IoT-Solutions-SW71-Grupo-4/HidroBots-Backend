package org.hidrobots.platform.deviceCommunication.application.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hidrobots.platform.deviceCommunication.domain.services.SoilAnalysisListener;
import org.springframework.stereotype.Service;

@Service
public class SoilAnalysisListenerHandler implements SoilAnalysisListener {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleSoilAnalysisResponseFromDevice(MqttMessage message) {
        var response = new String(message.getPayload());

        System.out.println(response);

        // try {
        //     var response = new String(message.getPayload());
        //     System.out.println(response);
        //     //var responseObj = objectMapper.readValue(response, SoilAnalysisResponse.class);
        //     //responseObj.setAnalysisDateTime(LocalDateTime.now());
        //     // El responseObj es un objeto q se peude guardar ya en la base de datos
        // } catch (JsonProcessingException e) {
        //     e.printStackTrace();
        // }
    }
}
