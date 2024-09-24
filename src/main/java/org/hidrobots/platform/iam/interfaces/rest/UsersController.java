package org.hidrobots.platform.iam.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.hidrobots.platform.iam.domain.model.queries.GetAllUsersQuery;
import org.hidrobots.platform.iam.domain.model.queries.GetUserByIdQuery;
import org.hidrobots.platform.iam.domain.services.UserQueryService;
import org.hidrobots.platform.iam.interfaces.rest.resources.UserResource;
import org.hidrobots.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping(value = "/api/v1/users", produces= MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users API")
public class UsersController {

    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toUserResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping(value= "/{userId}")
    public ResponseEntity<UserResource> getUserById(@RequestParam Long userId) {
        var GetUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(GetUserByIdQuery);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toUserResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
        
    }
    
    

}
