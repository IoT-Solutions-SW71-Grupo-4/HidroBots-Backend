package org.hidrobots.platform.crops.domain.services;

import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.commands.CreateCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.DeleteCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropNameCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateIrrigationTypeCommand;

import java.util.Optional;

public interface CropCommandService {
    Long handle(CreateCropCommand command); // Long -> retorna el id creado
    Optional<Crop> handle(UpdateCropNameCommand command); // Optional -> almacena datos y comprueba que no sea "null"
    Optional<Crop> handle(UpdateIrrigationTypeCommand command); // Optional -> almacena datos y comprueba que no sea "null"
    void handle(DeleteCropCommand command); // void -> no retorna nada

}
