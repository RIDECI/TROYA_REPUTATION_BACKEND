package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;


import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;
import org.springframework.stereotype.Component;

@Component
public class GoodRule implements BadgeRule {

    @Override
    public boolean applies(Profile profile) {
        return profile.getCalification() != null &&
                profile.getCalification().getAverage() >= 4.5;
    }

    @Override
    public Badge buildBadge() {
        return BadgeFactory.build("Buen", "Usuario altamente valorado");
    }
}