package org.hidrobots.platform.crops.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.shared.domain.model.entities.AuditableModel;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FarmerRegistry extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmerId;

    public FarmerRegistry(Long farmerId) {
        this.farmerId = farmerId;
    }
}

// almacena los farmers creados en el contexto de crops
