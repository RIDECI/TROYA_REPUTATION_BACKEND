package edu.dosw.rideci.domain.badge;

import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

public interface BadgeRule {
    boolean applies(ProfileDocument profile);
    BadgeDocument buildBadge();
}

