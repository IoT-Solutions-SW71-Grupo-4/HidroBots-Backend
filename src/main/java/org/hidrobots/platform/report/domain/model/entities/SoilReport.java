package org.hidrobots.platform.report.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportWithDeviceCodeCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class SoilReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date dateTime;

    @NotNull(message = "Temperature is required")
    private Double nitrogen;

    @NotNull(message = "Phosphorus is required")
    private Double phosphorus;

    @NotNull(message = "Potassium is required")
    private Double potassium;

    @NotNull(message = "Device is required")
    private Long deviceId;

    public SoilReport(CreateSoilReportCommand command) {
        this();
        this.nitrogen = command.nitrogen();
        this.phosphorus = command.phosphorus();
        this.potassium = command.potassium();
        this.deviceId = command.deviceId();
    }

    public SoilReport(CreateSoilReportWithDeviceCodeCommand command, Long deviceId) {
        this();
        this.nitrogen = command.getNitrogen();
        this.phosphorus = command.getPhosphorus();
        this.potassium = command.getPotassium();
        this.deviceId = deviceId;
    }
}
