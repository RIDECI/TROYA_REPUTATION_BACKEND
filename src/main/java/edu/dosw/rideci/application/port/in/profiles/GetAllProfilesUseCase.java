package edu.dosw.rideci.application.port.in.profiles;

import java.util.List;

import edu.dosw.rideci.domain.model.Profile;

public interface GetAllProfilesUseCase {
    List<Profile> getAllProfiles();

    
}
