package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.domain.model.Profile;

public interface CreateDriverProfileUseCase {
    Profile createDriverProfile(Profile profile);
    
}
