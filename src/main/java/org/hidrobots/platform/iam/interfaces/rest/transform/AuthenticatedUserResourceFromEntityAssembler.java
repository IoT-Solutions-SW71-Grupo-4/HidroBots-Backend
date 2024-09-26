package org.hidrobots.platform.iam.interfaces.rest.transform;


import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(
            user.getId(),
            user.getEmail(),
            token
        );
    }

}
