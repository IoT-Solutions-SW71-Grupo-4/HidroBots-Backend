package org.hidrobots.platform.crops.application.internal.queryServiceImpl;

import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.queries.GetAllCropsQuery;
import org.hidrobots.platform.crops.domain.model.queries.GetCropByIdQuery;
import org.hidrobots.platform.crops.domain.model.queries.GetCropsFromAFarmerQuery;
import org.hidrobots.platform.crops.domain.services.CropQueryService;
import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryServiceImpl implements CropQueryService {

    private final CropRepository cropRepository;

    @Autowired
    public CropQueryServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public List<Crop> handle(GetAllCropsQuery query) {
        return cropRepository.findAll();
    }

    @Override
    public List<Crop> handle(GetCropsFromAFarmerQuery query) {
        return cropRepository.findCropByFarmerId(query.farmerId());
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query) {
        return cropRepository.findById(query.id());
    }
}
