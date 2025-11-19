package edu.dosw.rideci.application.port.in;

import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;

public interface UpdateProfileUseCase {

    Profile updateProfile(Long id, ProfileRequestDTO profile);

}
