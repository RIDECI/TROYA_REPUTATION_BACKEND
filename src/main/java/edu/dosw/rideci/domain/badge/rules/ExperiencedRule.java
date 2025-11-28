package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;

import org.springframework.stereotype.Component;

import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;

@Component
public class ExperiencedRule implements BadgeRule {

    @Override
    public boolean applies(Profile profile) {
        int totalRatings = profile.getRatings() != null ? profile.getRatings().size() : 0;
        return totalRatings >= 50;
    }

    @Override
    public Badge buildBadge() {
        return BadgeFactory.build("Experimentado", "Usuario con alta experiencia");
    }
}