package org.hidrobots.platform.profiles.application.internal.queryCommandServiceImpl;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.domain.model.queries.GetAllFarmersQuery;
import org.hidrobots.platform.profiles.domain.model.queries.GetFarmerByIdQuery;
import org.hidrobots.platform.profiles.domain.services.FarmerQueryService;
import org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerQueryServiceImpl implements FarmerQueryService {

    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerQueryServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public List<Farmer> getAllFarmers(GetAllFarmersQuery query) {
        return farmerRepository.findAll();
    }

    @Override
    public Optional<Farmer> getFarmerById(GetFarmerByIdQuery query) {
        return farmerRepository.findById(query.farmerId());
    }
}
