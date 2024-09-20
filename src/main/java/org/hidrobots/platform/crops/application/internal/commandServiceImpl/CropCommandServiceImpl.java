package org.hidrobots.platform.crops.application.internal.commandServiceImpl;

import jakarta.transaction.Transactional;
import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.commands.CreateCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.DeleteCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropDescriptionCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropNameCommand;
import org.hidrobots.platform.crops.domain.services.CropCommandService;
import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropCommandServiceImpl implements CropCommandService {

    private final CropRepository cropRepository;

    @Autowired
    public CropCommandServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateCropCommand command) {
        var name = command.cropName();

        if(cropRepository.existsCropsByCropName(name)){
           throw new IllegalArgumentException("Crop with name " + name + "is already exist!!");
        }

        Crop crop = new Crop(
                command.cropName(),
                command.cropDescription()
        );

        cropRepository.save(crop);
        return crop.getId();
    }

    @Override
    @Transactional
    public Optional<Crop> handle(UpdateCropNameCommand command) {
        // buscamos en la bd si existe un cultivo con el id
        var crop = cropRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Course with id " + command.id() + "doesn't exist!!"));

        // si el nombre es diferente de null, de "string" y no esta vacio, actualiza la descripcion
        if (command.cropName() != null && !command.cropName().isBlank()) {
            crop.setCropName(command.cropName());
        }

        return Optional.of(cropRepository.save(crop));
    }

    @Override
    public Optional<Crop> handle(UpdateCropDescriptionCommand command) {
        // buscamos en la bd si existe un cultivo con el id
        var crop = cropRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Course with id " + command.id() + "doesn't exist!!"));

        // si la descripcion es diferente de null, de "string" y no esta vacio, actualiza la descripcion
        if (command.cropDescription() != null && !command.cropDescription().isBlank()) {
            crop.setCropDescription(command.cropDescription());
        }

        return Optional.of(cropRepository.save(crop));
    }

    @Override
    @Transactional
    public void handle(DeleteCropCommand command) {
        // verify if crop exist in the database
        if (!cropRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Crop with id " + command.id() + " doesn't exist!!");
        }
        try {
            cropRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting crop with id " + command.id());
        }
    }
}
