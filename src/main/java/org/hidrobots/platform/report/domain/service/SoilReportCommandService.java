package org.hidrobots.platform.report.domain.service;

import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportWithDeviceCodeCommand;

public interface SoilReportCommandService {
    Long createSoilReport(CreateSoilReportCommand command);
    Long createSoilReport(CreateSoilReportWithDeviceCodeCommand command);
}
