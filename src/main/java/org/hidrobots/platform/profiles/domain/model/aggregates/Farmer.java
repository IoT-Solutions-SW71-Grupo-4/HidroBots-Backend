package org.hidrobots.platform.profiles.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidrobots.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

/**
 * Farmer
 *
 * <p>
 *     This class represents a Farmer.
 * </p>
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Farmer extends AuditableAbstractAggregateRoot<Farmer> {

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    public Farmer(String username, String email, String phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

}
