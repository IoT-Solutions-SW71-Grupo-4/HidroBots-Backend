package org.hidrobots.platform.crops.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Crop extends AuditableAbstractAggregateRoot<Crop> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String cropName;

    @NotBlank(message = "Description is required")
    private String cropDescription;

    public Crop(String cropName, String cropDescription) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
    }
}
