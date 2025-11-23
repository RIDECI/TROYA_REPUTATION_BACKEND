package edu.dosw.rideci.application.port.in.rating;

import java.util.List;

import edu.dosw.rideci.domain.model.Badge;

public interface GetUserBadgesUseCase {
    List<Badge> getBadgesForUser(Long profileId);
}
