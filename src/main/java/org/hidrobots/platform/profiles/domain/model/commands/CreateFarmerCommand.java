package org.hidrobots.platform.profiles.domain.model.commands;

public record CreateFarmerCommand(String username, String email, String phoneNumber, String password) {
}
