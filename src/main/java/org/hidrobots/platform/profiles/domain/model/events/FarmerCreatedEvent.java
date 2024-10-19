package org.hidrobots.platform.profiles.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class FarmerCreatedEvent extends ApplicationEvent {

    private final Long farmerId;
    private final String email;

    public FarmerCreatedEvent(Object source, Long farmerId, String email) {
        super(source);
        this.farmerId = farmerId;
        this.email = email;
    }
}
