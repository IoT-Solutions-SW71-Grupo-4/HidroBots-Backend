package org.hidrobots.platform.iam.domain.services;



import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.domain.model.queries.GetAllUsersQuery;
import org.hidrobots.platform.iam.domain.model.queries.GetUserByEmailQuery;
import org.hidrobots.platform.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query); // retorna todos los usuarios
    Optional<User> handle(GetUserByIdQuery query); // retorna un usuario por id
    Optional<User> handle(GetUserByEmailQuery query); // retorna un usuario por username
}
