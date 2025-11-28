package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.domain.model.Profile;

public interface CreateProfileUseCase {
    Profile createInitialProfile(Profile profile); 
    Profile createDriverProfile(Profile profile);
    Profile createPassengerProfile(Profile profile);
    Profile createCompaniantProfile(Profile profile);
    
}
