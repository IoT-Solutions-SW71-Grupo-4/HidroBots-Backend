package org.hidrobots.platform.crops.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.crops.domain.model.commands.CreateCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropCommand;
import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;
import org.hidrobots.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;
import java.util.Date;

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

    @NotNull(message = "Irrigation type is required")
    @Enumerated(EnumType.STRING)
    private IrrigationType irrigationType;

    @NotNull(message = "Area is required")
    private Long area;

    @NotNull(message = "Planting date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private LocalDate plantingDate;

    @NotNull
    private Long farmerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crop_image_id", referencedColumnName = "id")
    private CropImage cropImage;


    public Crop(CreateCropCommand command) {
        this();
        this.cropName = command.cropName();
        this.irrigationType = command.irrigationType();
        this.area = command.area();
        this.plantingDate = command.plantingDate();
        this.farmerId = command.farmerId();
    }

    public Crop update(
            String cropName,
            IrrigationType irrigationType,
            Long area,
            LocalDate plantingDate,
            Long farmerId
    ) {
        this.cropName = cropName;
        this.irrigationType = irrigationType;
        this.area = area;
        this.plantingDate = plantingDate;
        this.farmerId = farmerId;
        return this;
    }
}
