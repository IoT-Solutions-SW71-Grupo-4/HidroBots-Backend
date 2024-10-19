package org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    boolean existsByEmail(String email);
    Optional<Farmer> findByEmail(String email);
}
