package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;
import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

public class DestacadoRule implements BadgeRule {

    @Override
    public boolean applies(ProfileDocument profile) {
        return profile.getCalification() != null &&
                profile.getCalification().getAverage() >= 4.0;
    }

    @Override
    public BadgeDocument buildBadge() {
        return BadgeFactory.build("Destacado", "Buen nivel de reputación");
    }

}

