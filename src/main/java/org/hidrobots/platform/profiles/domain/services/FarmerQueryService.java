package org.hidrobots.platform.profiles.domain.services;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.domain.model.queries.GetAllFarmersQuery;
import org.hidrobots.platform.profiles.domain.model.queries.GetFarmerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface FarmerQueryService {
    List<Farmer> getAllFarmers(GetAllFarmersQuery query);
    Optional<Farmer> getFarmerById(GetFarmerByIdQuery query);
}
