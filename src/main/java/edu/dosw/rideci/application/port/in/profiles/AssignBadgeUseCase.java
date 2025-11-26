package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;

public interface AssignBadgeUseCase {
    Profile execute(Long profileId);
}
