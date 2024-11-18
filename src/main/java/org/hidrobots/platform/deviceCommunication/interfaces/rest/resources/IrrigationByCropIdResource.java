package org.hidrobots.platform.deviceCommunication.interfaces.rest.resources;

public record IrrigationByCropIdResource(Long cropId, boolean irrigationStatus) {
}
