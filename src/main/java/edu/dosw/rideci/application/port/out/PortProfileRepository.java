package edu.dosw.rideci.application.port.out;

import java.util.List;

import edu.dosw.rideci.domain.model.Profile;

public interface PortProfileRepository {
    Profile saveProfile(Profile profile);
    Profile getProfileById(Long id);
    List<Profile> getAllProfiles();
    Profile updateProfile(Long id, Profile profile);
    void deleteProfileById(Long id);
    
}
