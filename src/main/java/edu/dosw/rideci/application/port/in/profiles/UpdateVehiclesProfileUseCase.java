package edu.dosw.rideci.application.port.in.profiles;

import java.util.List;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.VehicleRequestDTO;

public interface UpdateVehiclesProfileUseCase {
    Profile updateVehiclesProfile(Long id, List<VehicleRequestDTO> vehiclesRequest);
}