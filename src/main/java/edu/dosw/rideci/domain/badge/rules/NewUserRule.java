package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;
import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

@Component
public class NewUserRule implements BadgeRule {

    @Override
    public boolean applies(Profile profile) {
        int totalRatings = profile.getRatings() != null ? profile.getRatings().size() : 0;
        return totalRatings <= 10;
    }

    @Override
    public Badge buildBadge() {
        return BadgeFactory.build("Nuevo User", "Usuario recién registrado");
    }
}