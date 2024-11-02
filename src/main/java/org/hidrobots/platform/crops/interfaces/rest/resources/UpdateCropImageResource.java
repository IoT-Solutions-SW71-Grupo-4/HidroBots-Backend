package org.hidrobots.platform.crops.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record UpdateCropImageResource (
        MultipartFile cropImage
)
{
}
