package edu.dosw.rideci.application.port.in;

import edu.dosw.rideci.domain.model.Profile;

public interface CreateProfileUseCase {
    
    Profile createProfile(Profile profile);
    
}
