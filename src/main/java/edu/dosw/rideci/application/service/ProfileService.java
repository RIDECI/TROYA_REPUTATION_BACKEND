package edu.dosw.rideci.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.in.CreateProfileUseCase;
import edu.dosw.rideci.application.port.in.DeleteProfileUseCase;
import edu.dosw.rideci.application.port.in.GetAllProfilesUseCase;
import edu.dosw.rideci.application.port.in.GetProfileUseCase;
import edu.dosw.rideci.application.port.in.UpdateProfileUseCase;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService implements CreateProfileUseCase,DeleteProfileUseCase,
                    GetProfileUseCase,GetAllProfilesUseCase,UpdateProfileUseCase {
                
    private final PortProfileRepository portProfileRepository;
    private final InitialProfileMapper profileMapper;

    @Override
    public Profile createProfile(Profile profile){
        return portProfileRepository.saveProfile(profile);
    }

    @Override
    public Profile updateProfile(Long id, ProfileRequestDTO profile){
        Profile updatedProfile = profileMapper.toDomain(profile);
        return portProfileRepository.updateProfile(id, updatedProfile);
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
