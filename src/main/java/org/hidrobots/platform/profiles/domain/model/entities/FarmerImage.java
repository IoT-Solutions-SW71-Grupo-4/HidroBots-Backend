package org.hidrobots.platform.profiles.domain.model.entities;

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
public class FarmerImage extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    private String imageId;

    public FarmerImage(String name, String imageUrl, String imageId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

}
