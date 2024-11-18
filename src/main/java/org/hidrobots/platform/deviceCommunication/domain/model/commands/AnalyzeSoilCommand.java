package org.hidrobots.platform.deviceCommunication.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeSoilCommand {
    private String deviceCode;
}
