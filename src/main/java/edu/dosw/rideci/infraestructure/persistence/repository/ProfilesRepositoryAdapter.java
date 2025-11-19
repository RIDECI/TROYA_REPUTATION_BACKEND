package edu.dosw.rideci.infraestructure.persistence.repository;

import java.util.List;

import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.ProfileMapper;

public class ProfilesRepositoryAdapter implements PortProfileRepository {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    
    
}
