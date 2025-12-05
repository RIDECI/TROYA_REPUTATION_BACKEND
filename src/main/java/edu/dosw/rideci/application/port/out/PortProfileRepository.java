package edu.dosw.rideci.application.port.out;

import java.util.List;

import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;

public interface PortProfileRepository {
    
    Profile createInitialProfile(Profile profile);
    Profile createDriverProfile(Profile profile);
    Profile createPassengerProfile(Profile profile);
    Profile createCompaniantProfile(Profile profile);
    Profile getProfileById(Long userId);
    List<Profile> getAllProfiles();
    Profile updateProfile(Long userId, Profile profile);
    Profile updateVehiclesProfile(Long userId, Profile profile);
    void deleteProfileById(Long userId);
    Profile assignBadge(Long userId, Profile profile);
    Vehicle uploadVehicleData(Vehicle vehicleData);
    

}
