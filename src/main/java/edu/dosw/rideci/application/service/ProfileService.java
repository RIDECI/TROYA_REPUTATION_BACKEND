package edu.dosw.rideci.application.service;

import java.util.List;

import edu.dosw.rideci.application.port.in.profiles.*;
import edu.dosw.rideci.domain.badge.engine.BadgeEngine;
import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.request.VehicleRequestDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService implements CreateProfileUseCase,DeleteProfileUseCase,GetProfileUseCase,
                    GetAllProfilesUseCase,UpdateProfileUseCase,UpdateVehiclesProfileUseCase, AssignBadgeUseCase,
                    UploadVehicleDataUseCase,GetVehiclesByProfileUseCase, GetVehicleByPlateUseCase {

    private final PortProfileRepository portProfileRepository;
    private final InitialProfileMapper profileMapper;
    private final BadgeEngine badgeEngine;

    @Override
    public Profile createInitialProfile(Profile profile){
        return portProfileRepository.createInitialProfile(profile);
    }
    @Override
    public Profile createDriverProfile(Profile profile){
        return portProfileRepository.createDriverProfile(profile);
    }

    @Override
    public Profile createPassengerProfile(Profile profile){
        return portProfileRepository.createPassengerProfile(profile);
    }

    @Override
    public Profile createCompaniantProfile(Profile profile){
        return portProfileRepository.createCompaniantProfile(profile);
    }

    @Override
    public Profile updateProfile(Long id, ProfileRequestDTO profile){
        Profile updatedProfile = profileMapper.toDomain(profile);
        return portProfileRepository.updateProfile(id, updatedProfile);
    }

    @Override
    public Profile updateVehiclesProfile(Long id, List<VehicleRequestDTO> vehiclesRequest) {
        List<Vehicle> vehicles = profileMapper.toVehicleListDomain(vehiclesRequest);

        Profile profileWithVehicles = Profile.builder()
            .vehicles(vehicles)
            .build();

        return portProfileRepository.updateVehiclesProfile(id, profileWithVehicles);
    }

    @Override
    public Profile getProfileById(Long id){
        return portProfileRepository.getProfileById(id);
    }

    @Override
    public void deleteProfileById(Long id){
        portProfileRepository.deleteProfileById(id);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return portProfileRepository.getAllProfiles();

    }

    @Override
    public Profile assignBadge(Long profileId) {

        Profile existingProfile = portProfileRepository.getProfileById(profileId);

        List<Badge> newBadges = badgeEngine.evaluate(existingProfile);
        existingProfile.setBadges(newBadges);

        return portProfileRepository.assignBadge(profileId, existingProfile);
    }

    @Override
    public List<Vehicle> getVehicles(Long profileId) {

        Profile profile = portProfileRepository.getProfileById(profileId);

        return profile.getVehicles();
    }

    @Override
    public Vehicle getVehicle(Long profileId, String vehiclePlate) {

        Profile profile = portProfileRepository.getProfileById(profileId);

        return profile.getVehicles().stream()
                .filter(v -> v.getVehiclePlate().equalsIgnoreCase(vehiclePlate))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Vehicle with plate " + vehiclePlate +
                                " not found for profile " + profileId)
                );
    }

    @Override
    public Vehicle uploadVehicleData(Vehicle vehicleData) {
        return portProfileRepository.uploadVehicleData(vehicleData);
    }


}

