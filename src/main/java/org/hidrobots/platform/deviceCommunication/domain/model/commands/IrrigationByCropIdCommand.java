package org.hidrobots.platform.deviceCommunication.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IrrigationByCropIdCommand {
    private Long cropId;
    private boolean irrigationStatus;
}
