package org.hidrobots.platform.iam.interfaces.rest.transform;


import org.hidrobots.platform.iam.domain.model.entities.Role;
import org.hidrobots.platform.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toRoleResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }

}
