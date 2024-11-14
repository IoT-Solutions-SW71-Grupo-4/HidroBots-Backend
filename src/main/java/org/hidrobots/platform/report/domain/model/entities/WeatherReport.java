package org.hidrobots.platform.report.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.crops.domain.model.entities.Device;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WeatherReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date dateTime;

    @NotBlank(message = "Temperature is required")
    private String temperature;

    @NotBlank(message = "Humidity is required")
    private String humidity;

    @NotBlank(message = "Device is required")
    private Long deviceId;

    public WeatherReport(CreateWeatherReportCommand command) {
        this();
        this.temperature = command.temperature();
        this.humidity = command.humidity();
        this.deviceId = command.deviceId();
    }
}
