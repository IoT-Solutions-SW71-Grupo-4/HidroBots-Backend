package org.hidrobots.platform.iam.domain.services;


import org.hidrobots.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
