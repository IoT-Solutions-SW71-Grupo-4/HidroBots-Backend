package org.hidrobots.platform.report.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWeatherReportWithDeviceCodeCommand {
    private Double temperature;
    private Double humidity;
    private String deviceCode;
}
