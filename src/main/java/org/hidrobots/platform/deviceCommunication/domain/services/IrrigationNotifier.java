package org.hidrobots.platform.deviceCommunication.domain.services;

import org.hidrobots.platform.deviceCommunication.domain.model.commands.IrrigationByCropIdCommand;
import org.hidrobots.platform.deviceCommunication.domain.model.commands.IrrigationCommand;

public interface IrrigationNotifier {
    String notifyIrrigationSystem(IrrigationCommand command);
    String notifyIrrigationSystemForCrop(IrrigationByCropIdCommand command);
}
