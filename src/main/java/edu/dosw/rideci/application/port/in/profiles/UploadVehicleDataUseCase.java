package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.domain.model.Vehicle;

public interface UploadVehicleDataUseCase {
    Vehicle uploadVehicleData(Vehicle vehicleData);
}
