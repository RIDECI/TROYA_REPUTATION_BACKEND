package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;
import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

public class ExperimentadoRule implements BadgeRule {

    @Override
    public boolean applies(ProfileDocument profile) {
        int totalRatings = profile.getRatings() != null ? profile.getRatings().size() : 0;
        return totalRatings >= 50;
    }

    @Override
    public BadgeDocument buildBadge() {
        return BadgeFactory.build("Experimentado", "Usuario con alta experiencia");
    }

}
