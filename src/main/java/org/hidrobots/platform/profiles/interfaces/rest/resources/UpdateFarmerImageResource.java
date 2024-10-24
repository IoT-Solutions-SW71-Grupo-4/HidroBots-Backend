package org.hidrobots.platform.profiles.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record UpdateFarmerImageResource(
        MultipartFile farmerImage
) {
}
