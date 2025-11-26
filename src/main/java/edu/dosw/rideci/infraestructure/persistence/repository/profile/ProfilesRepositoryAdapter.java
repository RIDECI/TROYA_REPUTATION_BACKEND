package edu.dosw.rideci.infraestructure.persistence.repository.profile;

import java.util.List;

import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import org.springframework.stereotype.Repository;

import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import edu.dosw.rideci.exceptions.ProfileNotFoundException;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.VehicleDocument;
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
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .profileType(ProfileType.COMPANION)
                .vehicles(savedProfile.getVehicles())
                .ratings(savedProfile.getRatings())
                .badges(savedProfile.getBadges())
                .build();
        return profileMapper.toDomain(createdProfile);
    }

    @Override
    public Profile createPassengerProfile(Profile profile) {
        ProfileDocument profileDocument = profileMapper.toDocument(profile);
        ProfileDocument savedProfile = profileRepository.save(profileDocument);
        ProfileDocument createdProfile = ProfileDocument.builder()
                .id(savedProfile.getId())
                .name(savedProfile.getName())
                .calification(savedProfile.getCalification())
                .profileType(ProfileType.PASSENGER)
                .vehicles(savedProfile.getVehicles())
                .ratings(savedProfile.getRatings())
                .badges(savedProfile.getBadges())
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
        profileToUpdate.setProfileType(profile.getProfileType());

        ProfileDocument profileUpdated = profileRepository.save(profileToUpdate);

        return profileMapper.toDomain(profileUpdated);
    }

    @Override
    public Profile updateVehiclesProfile(Long id, Profile profile) {
        ProfileDocument profileToUpdate = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Doesnt exist the profile with id: " + id));

        List<VehicleDocument> vehicleDocs = profileMapper.toVehicleDocumentList(profile.getVehicles());
        profileToUpdate.setVehicles(vehicleDocs);

        ProfileDocument profileUpdated = profileRepository.save(profileToUpdate);

        return profileMapper.toDomain(profileUpdated);
    }

    @Override
    public void deleteProfileById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile assignBaadge(Long id, Profile profile){
        ProfileDocument profileDocument = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("The profile with id: " + id + " doesnt exist"));

        // Se obtienen los ratings
        int totalRatings = profileDocument.getRatings() != null ? profileDocument.getRatings().size() : 0;

        // obtenemos el promedio
        double average = 0.0;
        if (profileDocument.getCalification() != null) {
            average = profileDocument.getCalification().getAverage();
        }

        // Reiniciamos badges??
        profileDocument.setBadges(new java.util.ArrayList<>());

        // condiciones de los badges por cantidad
        if (totalRatings <= 10) {
            addBadge(profileDocument, "Nuevo User", "Usuario recién registrado");
        }

        if (totalRatings > 10) {
            addBadge(profileDocument, "Frecuente", "Usuario activo en la plataforma");
        }

        if (totalRatings >= 50) {
            addBadge(profileDocument, "Experimentado", "Usuario con alta experiencia");
        }

        // Badge por nota
        if (average >= 4.0) {
            addBadge(profileDocument, "Destacado", "Buen nivel de reputación");
        }

        if (average >= 4.5) {
            addBadge(profileDocument, "Buen", "Usuario altamente valorado");
        }

        if (average >= 4.8) {
            addBadge(profileDocument, "Excelente", "Usuario sobresaliente");
        }

        // Guardar cambios
        ProfileDocument updated = profileRepository.save(profileDocument);

        return profileMapper.toDomain(updated);
    }

    private void addBadge(ProfileDocument profile, String name, String description) {
        boolean exists = profile.getBadges().stream()
                .anyMatch(b -> b.getName().equalsIgnoreCase(name));

        if (exists) return;;

        BadgeDocument badge = new BadgeDocument();
        badge.setName(name);
        badge.setDescription(description);
        badge.setPathImageBlackAndWhite("/badges" + name.replace(" ", "_").toLowerCase() + "_bw.png");
        badge.setPathImageColor("/badges" + name.replace(" ", "_").toLowerCase() + "_color.png");
        badge.setActive(true);

        profile.getBadges().add(badge);
    }

}
