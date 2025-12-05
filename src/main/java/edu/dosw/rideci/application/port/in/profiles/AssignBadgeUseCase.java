package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.domain.model.Profile;

public interface AssignBadgeUseCase {
    Profile assignBadge(Long userId);
}