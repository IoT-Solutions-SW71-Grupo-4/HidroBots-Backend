package org.hidrobots.platform.iam.domain.model.commands;



import org.hidrobots.platform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String fullName, String email, String password, List<Role> roles) {

}
