package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;
import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

public class NuevoUsuarioRule implements BadgeRule {

    @Override
    public boolean applies(ProfileDocument profile) {
        int totalRatings = profile.getRatings() != null ? profile.getRatings().size() : 0;
        return totalRatings <= 10;
    }

    @Override
    public BadgeDocument buildBadge() {
        return BadgeFactory.build("Nuevo User", "Usuario recién registrado");
    }

}
