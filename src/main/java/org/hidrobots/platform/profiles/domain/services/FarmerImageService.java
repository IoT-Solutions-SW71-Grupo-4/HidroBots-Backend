package org.hidrobots.platform.profiles.domain.services;

import org.hidrobots.platform.profiles.domain.model.entities.FarmerImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FarmerImageService {
    FarmerImage uploadImage(MultipartFile multipartFile) throws IOException;
    void deleteImage(FarmerImage farmerImage) throws IOException;
}
