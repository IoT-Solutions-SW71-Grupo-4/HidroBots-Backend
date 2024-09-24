package org.hidrobots.platform.iam.domain.services;



import org.hidrobots.platform.iam.domain.model.entities.Role;
import org.hidrobots.platform.iam.domain.model.queries.GetAllRolesQuery;
import org.hidrobots.platform.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query); // retorna todos los roles
    Optional<Role> handle(GetRoleByNameQuery query); // retorna un rol por nombre

}
