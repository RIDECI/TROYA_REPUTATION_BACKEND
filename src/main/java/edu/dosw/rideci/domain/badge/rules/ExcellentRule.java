package edu.dosw.rideci.domain.badge.rules;

import edu.dosw.rideci.domain.badge.BadgeRule;

import org.springframework.stereotype.Component;

import edu.dosw.rideci.domain.badge.BadgeFactory;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;

@Component  
public class ExcellentRule implements BadgeRule {

    @Override
    public boolean applies(Profile profile) {
        return profile.getCalification() != null &&
                profile.getCalification().getAverage() >= 4.8;
    }

    @Override
    public Badge buildBadge() {
        return BadgeFactory.build("Excelente", "Usuario sobresaliente");
    }
}