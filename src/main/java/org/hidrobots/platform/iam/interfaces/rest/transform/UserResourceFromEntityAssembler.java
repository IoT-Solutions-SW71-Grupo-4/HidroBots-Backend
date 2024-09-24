package org.hidrobots.platform.iam.interfaces.rest.transform;


import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toUserResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(role -> role.getStringName()).toList();

        return new UserResource(
            entity.getId(),
            entity.getEmail(),
            roles
        );
    }

}
