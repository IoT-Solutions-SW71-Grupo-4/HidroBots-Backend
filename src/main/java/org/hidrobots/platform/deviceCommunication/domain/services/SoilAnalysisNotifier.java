package org.hidrobots.platform.deviceCommunication.domain.services;

import org.hidrobots.platform.deviceCommunication.domain.model.commands.AnalyzeSoilCommand;

public interface SoilAnalysisNotifier {
    String sendAnalysisRequestToDevice(AnalyzeSoilCommand command);
}
