package org.hidrobots.platform.report.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSoilReportWithDeviceCodeCommand {
    public Double nitrogen;
    public Double phosphorus;
    public Double potassium;
    public String deviceCode;
}
