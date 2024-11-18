package org.hidrobots.platform.iam.interfaces.rest.transform;



import org.hidrobots.platform.iam.domain.model.commands.SignUpCommand;
import org.hidrobots.platform.iam.domain.model.entities.Role;
import org.hidrobots.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null
                ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList()
                : new ArrayList<Role>();
        System.out.print("Roles:");
        
        System.out.println(!roles.isEmpty() ? roles.get(0).getName().name() : "No roles");
        return new SignUpCommand(resource.fullName(), resource.email(), resource.password(), roles);
    }

}