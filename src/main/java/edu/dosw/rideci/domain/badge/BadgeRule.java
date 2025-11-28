package edu.dosw.rideci.domain.badge;

import edu.dosw.rideci.domain.model.Badge; 
import edu.dosw.rideci.domain.model.Profile; 


public interface BadgeRule {
    boolean applies(Profile profile);
    Badge buildBadge();
}