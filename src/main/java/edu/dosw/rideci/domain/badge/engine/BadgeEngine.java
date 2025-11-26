package edu.dosw.rideci.domain.badge.engine;

import edu.dosw.rideci.domain.badge.BadgeRule;
import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

import java.util.List;
import java.util.stream.Collectors;

public class BadgeEngine {

    private final List<BadgeRule> rules;

    public BadgeEngine(List<BadgeRule> rules) {
        this.rules = rules;
    }

    public List<BadgeDocument> evaluate(ProfileDocument profile) {
        return rules.stream()
                .filter(rule -> rule.applies(profile))
                .map(BadgeRule::buildBadge)
                .collect(Collectors.toList());
    }
}

