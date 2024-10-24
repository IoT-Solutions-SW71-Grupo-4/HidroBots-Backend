package org.hidrobots.platform.profiles.application.internal.commandServiceImpl;

import org.hidrobots.platform.profiles.domain.model.entities.FarmerImage;
import org.hidrobots.platform.profiles.domain.services.FarmerImageService;
import org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories.FarmerImageRepository;
import org.hidrobots.platform.shared.domain.services.CloudinaryCommandService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FarmerImageServiceImpl implements FarmerImageService {

    private final CloudinaryCommandService cloudinaryCommandService;
    private final FarmerImageRepository farmerImageRepository;

    public FarmerImageServiceImpl(CloudinaryCommandService cloudinaryCommandService, FarmerImageRepository farmerImageRepository) {
        this.cloudinaryCommandService = cloudinaryCommandService;
        this.farmerImageRepository = farmerImageRepository;
    }

    @Override
    public FarmerImage uploadImage(MultipartFile multipartFile) throws IOException {
        Map uploadImage = cloudinaryCommandService.uploadImage(multipartFile);
        String imageUrl = (String) uploadImage.get("url");
        String imageId = (String) uploadImage.get("public_id");
        FarmerImage farmerImage = new FarmerImage(multipartFile.getOriginalFilename(), imageUrl, imageId);

        return farmerImageRepository.save(farmerImage);
    }

    @Override
    public void deleteImage(FarmerImage farmerImage) throws IOException {
        cloudinaryCommandService.deleteImage(farmerImage.getImageId());
        farmerImageRepository.delete(farmerImage);
    }
}
