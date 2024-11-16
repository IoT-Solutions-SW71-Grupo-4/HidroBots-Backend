package org.hidrobots.platform.report.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportWithDeviceCodeCommand;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class WeatherReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date dateTime;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @NotNull(message = "Humidity is required")
    private Double humidity;

    @NotNull(message = "Device is required")
    private Long deviceId;

    public WeatherReport(CreateWeatherReportCommand command) {
        this();
        this.temperature = command.temperature();
        this.humidity = command.humidity();
        this.deviceId = command.deviceId();
    }

    public WeatherReport(CreateWeatherReportWithDeviceCodeCommand command, Long deviceId) {
        this();
        this.temperature = command.getTemperature();
        this.humidity = command.getHumidity();
        this.deviceId = deviceId;
    }
}
