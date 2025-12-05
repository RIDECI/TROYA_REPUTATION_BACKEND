package edu.dosw.rideci.application.port.out;

import java.util.List;

import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.infraestructure.controller.dto.request.VehicleRequestDTO;

public interface PortProfileRepository {
    
    Profile createInitialProfile(Profile profile);
    Profile createDriverProfile(Profile profile);
    Profile createPassengerProfile(Profile profile);
    Profile createCompaniantProfile(Profile profile);
    Profile getProfileById(Long id);
    List<Profile> getAllProfiles();
    Profile updateProfile(Long id, Profile profile);
    Profile updateVehiclesProfile(Long id, Profile profile);
    void deleteProfileById(Long id);
    Profile assignBadge(Long profileId, Profile profile);
    Vehicle uploadVehicleData(Vehicle vehicleData);
    

}
