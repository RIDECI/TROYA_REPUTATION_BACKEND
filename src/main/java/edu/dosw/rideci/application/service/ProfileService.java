package edu.dosw.rideci.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.in.profiles.CreateCompaniantProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.CreateDriverProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.CreatePassengerProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.DeleteProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.GetAllProfilesUseCase;
import edu.dosw.rideci.application.port.in.profiles.GetProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.UpdateProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.UpdateVehiclesProfileUseCase;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService implements CreateDriverProfileUseCase,CreatePassengerProfileUseCase,
                    CreateCompaniantProfileUseCase,DeleteProfileUseCase,GetProfileUseCase,
                    GetAllProfilesUseCase,UpdateProfileUseCase,UpdateVehiclesProfileUseCase {
                
    private final PortProfileRepository portProfileRepository;
    private final InitialProfileMapper profileMapper;

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
    public Profile updateVehiclesProfile(Long id, ProfileRequestDTO profile){
        Profile updatedProfile = profileMapper.toDomain(profile);
        return portProfileRepository.updateVehiclesProfile(id, updatedProfile);
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
}
