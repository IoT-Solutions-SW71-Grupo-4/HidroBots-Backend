package org.hidrobots.platform.deviceCommunication.interfaces.rest.resources;

public record IrrigationCommandResource(String deviceCode, boolean irrigationStatus) {
}
