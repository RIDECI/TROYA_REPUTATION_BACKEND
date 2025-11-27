package edu.dosw.rideci.domain.badge.engine;

import edu.dosw.rideci.domain.badge.BadgeRule;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;

import java.util.List;
import java.util.stream.Collectors;

public class BadgeEngine {

    private final List<BadgeRule> rules;

    public BadgeEngine(List<BadgeRule> rules) {
        this.rules = rules;
    }

    public List<Badge> evaluate(Profile profile) {
        return rules.stream()
                .filter(rule -> rule.applies(profile))
                .map(BadgeRule::buildBadge)
                .collect(Collectors.toList());
    }
}