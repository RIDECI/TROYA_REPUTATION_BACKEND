package edu.dosw.rideci.application.port.in.profiles;

import java.util.List;
import edu.dosw.rideci.domain.model.Vehicle;

public interface GetVehiclesByProfileUseCase {
    List<Vehicle> getVehicles(Long userId);
}
