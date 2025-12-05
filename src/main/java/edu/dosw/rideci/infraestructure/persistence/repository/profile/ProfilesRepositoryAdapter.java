package edu.dosw.rideci.infraestructure.persistence.repository.profile;

import java.util.List;
import org.springframework.stereotype.Repository;

import edu.dosw.rideci.application.events.ProfileEvent;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import edu.dosw.rideci.exceptions.ProfileNotFoundException;
import edu.dosw.rideci.infraestructure.config.RabbitEventPublisher;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.VehicleDocument;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProfilesRepositoryAdapter implements PortProfileRepository {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final RabbitEventPublisher eventPublisher;

    @Override
    public Profile createInitialProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .userId(savedProfile.getUserId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .email(savedProfile.getEmail())
                .address(savedProfile.getAddress())
                .birthDate(savedProfile.getBirthDate())
                .identificationNumber(savedProfile.getIdentificationNumber())
                .profileType(ProfileType.NOT_DEFINED)
                .vehicles(savedProfile.getVehicles())
                .ratings(savedProfile.getRatings())
                .badges(savedProfile.getBadges())
                .phoneNumber(savedProfile.getPhoneNumber())
                .identificationType(savedProfile.getIdentificationType())
                .build();
                
        ProfileDocument savedDocument = profileRepository.save(createdProfile);
        ProfileEvent profileEvent = profileMapper.toUserEvent(savedDocument);
        eventPublisher.publish(profileEvent,"profile.exchange", "profile.created");
        return profileMapper.toDomain(createdProfile);
    }
    
    @Override
    public Profile createDriverProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .userId(savedProfile.getUserId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .email(savedProfile.getEmail())
                .address(savedProfile.getAddress())
                .birthDate(savedProfile.getBirthDate())
                .identificationNumber(savedProfile.getIdentificationNumber())
                .profileType(ProfileType.DRIVER)
                .vehicles(savedProfile.getVehicles())
                .ratings(savedProfile.getRatings())
                .badges(savedProfile.getBadges())
                .phoneNumber(savedProfile.getPhoneNumber())
                .identificationType(savedProfile.getIdentificationType())
                .build();
                
        ProfileDocument savedDocument = profileRepository.save(createdProfile);
        ProfileEvent profileEvent = profileMapper.toUserEvent(savedDocument);
        eventPublisher.publish(profileEvent,"profile.exchange", "profile.created");
        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile createCompaniantProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .userId(savedProfile.getUserId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .email(savedProfile.getEmail())
                .address(savedProfile.getAddress())
                .birthDate(savedProfile.getBirthDate())
                .identificationNumber(savedProfile.getIdentificationNumber())
                .profilePictureUrl(savedProfile.getProfilePictureUrl())
                .profileType(ProfileType.COMPANION)
                .ratings(savedProfile.getRatings())
                .badges(savedProfile.getBadges())
                .phoneNumber(savedProfile.getPhoneNumber())
                .identificationType(savedProfile.getIdentificationType())
                .build();

        ProfileDocument savedDocument = profileRepository.save(createdProfile);
        ProfileEvent profileEvent = profileMapper.toUserEvent(savedDocument);
        eventPublisher.publish(profileEvent,"profile.exchange", "profile.created");
        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile createPassengerProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .userId(savedProfile.getUserId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .email(savedProfile.getEmail())
                .address(savedProfile.getAddress())
                .birthDate(savedProfile.getBirthDate())
                .identificationNumber(savedProfile.getIdentificationNumber())
                .profileType(ProfileType.PASSENGER)
                .vehicles(savedProfile.getVehicles())
                .ratings(savedProfile.getRatings())
                .badges(savedProfile.getBadges())
                .phoneNumber(savedProfile.getPhoneNumber())
                .identificationType(savedProfile.getIdentificationType())
                .build();

        ProfileDocument savedDocument = profileRepository.save(createdProfile);
        ProfileEvent profileEvent = profileMapper.toUserEvent(savedDocument);
        eventPublisher.publish(profileEvent,"profile.exchange", "profile.created");

        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile getProfileById(Long userId) {

        ProfileDocument profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("The profile with id: {id}, doesnt exists "));

        return profileMapper.toDomain(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<ProfileDocument> allProfiles = profileRepository.findAll();

        return profileMapper.toListDomain(allProfiles);
    }

    @Override
    public Profile updateProfile(Long userId, Profile profile) {
        ProfileDocument profileToUpdate = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Doesnt exist the profile with id: {id}"));

        profileToUpdate.setName(profile.getName());
        profileToUpdate.setProfileType(profile.getProfileType());
        profileToUpdate.setPhoneNumber(profile.getPhoneNumber());

        ProfileDocument profileUpdated = profileRepository.save(profileToUpdate);

        return profileMapper.toDomain(profileUpdated);
    }

    @Override
    public Profile updateVehiclesProfile(Long userId, Profile profile) {
        ProfileDocument profileToUpdate = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Doesnt exist the profile with id: " + userId));

        List<VehicleDocument> vehicleDocs = profileMapper.toVehicleDocumentList(profile.getVehicles());
        
        profileToUpdate.setVehicles(vehicleDocs);

        ProfileDocument profileUpdated = profileRepository.save(profileToUpdate);

        return profileMapper.toDomain(profileUpdated);
    }

    @Override
    public void deleteProfileById(Long userId) {
        profileRepository.deleteByUserId(userId);
    }

    @Override
    public Profile assignBadge(Long userId, Profile profile) {

        ProfileDocument profileDoc = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        profileDoc.setBadges(profileMapper.toDocument(profile).getBadges());

        return profileMapper.toDomain(profileRepository.save(profileDoc));
    }
    @Override
    public Vehicle uploadVehicleData(Vehicle vehicleData) {

        VehicleDocument vehicleDocument = VehicleDocument.builder()
                .vehiclePlate(vehicleData.getVehiclePlate())
                .color(vehicleData.getColor())
                .vehicleType(vehicleData.getVehicleType())
                .vehicleModel(vehicleData.getVehicleModel())
                .vehiclePhoto(vehicleData.getVehiclePhoto())
                .build();
        
        return profileMapper.toVehicleDomain(vehicleDocument);
    }



}
