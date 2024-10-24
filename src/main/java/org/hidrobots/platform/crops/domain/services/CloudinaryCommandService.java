package org.hidrobots.platform.crops.domain.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryCommandService {
    Map uploadImage(MultipartFile multipartFile) throws IOException;
    Map deleteImage(String imageId) throws IOException;
}
