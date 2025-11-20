package edu.dosw.rideci.infraestructure.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import edu.dosw.rideci.exceptions.ProfileNotFoundException;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProfilesRepositoryAdapter implements PortProfileRepository {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Profile createDriverProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        //Evento Borrador 
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .profileType(ProfileType.DRIVER)
                .vehicles(savedProfile.getVehicles())
                .build();
        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile createCompaniantProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        //Evento Borrador
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .profileType(ProfileType.COMPANIANT)
                .vehicles(savedProfile.getVehicles())
                .build();
        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile createPassengerProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        //Evento Borrador
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .profileType(ProfileType.PASSENGER)
                .vehicles(savedProfile.getVehicles())
                .build();
        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile getProfileById(Long id) {
        
        ProfileDocument profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("The profile with id: {id}, doesnt exists "));

        return profileMapper.toDomain(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<ProfileDocument> allProfiles = profileRepository.findAll();

        return profileMapper.toListDomain(allProfiles);
    }

    @Override
    public Profile updateProfile(Long id, Profile profile) {
        ProfileDocument profileToUpdate = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Doesnt exist the profile with id: {id}"));

        profileToUpdate.setName(profile.getName());
        profileToUpdate.setCalification(profile.getCalification());
        profileToUpdate.setProfileType(profile.getProfileType());
        profileToUpdate.setVehicles(profile.getVehicles());

        ProfileDocument profileUpdated = profileRepository.save(profileToUpdate);

        return profileMapper.toDomain(profileUpdated);
    }

    @Override
    public Profile updateVehiclesProfile(Long id, Profile profile) {
        ProfileDocument profileToUpdate = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Doesnt exist the profile with id: " + id));

        profileToUpdate.setVehicles(profile.getVehicles());

        ProfileDocument profileUpdated = profileRepository.save(profileToUpdate);

        return profileMapper.toDomain(profileUpdated);
    }


    @Override
    public void deleteProfileById(Long id) {
        profileRepository.deleteById(id);
    }


    
}
