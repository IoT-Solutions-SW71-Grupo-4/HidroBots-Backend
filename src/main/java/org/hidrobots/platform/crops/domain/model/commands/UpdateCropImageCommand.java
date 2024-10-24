package org.hidrobots.platform.crops.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record UpdateCropImageCommand(
        Long id,
        MultipartFile cropImage
) {
}
