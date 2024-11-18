package org.hidrobots.platform.devices.domain.model.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.devices.domain.model.commands.CreateDeviceCommand;
import org.hidrobots.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Device extends AuditableAbstractAggregateRoot<Device> {

    @Column(nullable = false, unique = true)
    private String deviceCode;


    @Column(nullable = true)
    private Long cropId;

    public Device(CreateDeviceCommand command) {
        this();
        this.cropId = command.cropId();
        this.deviceCode = UUID.randomUUID().toString();
    }

    public Device() {
        this.deviceCode = UUID.randomUUID().toString();
    }

}




