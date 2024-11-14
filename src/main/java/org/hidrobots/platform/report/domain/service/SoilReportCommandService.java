package org.hidrobots.platform.report.domain.service;

import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportCommand;

public interface SoilReportCommandService {
    Long createSoilReport(CreateSoilReportCommand command);
}
