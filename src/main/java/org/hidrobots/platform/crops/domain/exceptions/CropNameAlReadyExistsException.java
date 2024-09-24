package org.hidrobots.platform.crops.domain.exceptions;

public class CropNameAlReadyExistsException extends IllegalArgumentException {
    public CropNameAlReadyExistsException(String message) {
        super(message);
    }
}
