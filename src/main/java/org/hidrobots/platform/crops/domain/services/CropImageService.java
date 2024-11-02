package org.hidrobots.platform.crops.domain.services;

import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CropImageService {

    CropImage uploadImage(MultipartFile file) throws IOException;
    void deleteImage(CropImage cropImage) throws IOException;
}
