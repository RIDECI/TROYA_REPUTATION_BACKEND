package edu.dosw.rideci.domain.badge;

import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;

public class BadgeFactory {

    public static BadgeDocument build(String name, String description) {
        BadgeDocument badge = new BadgeDocument();
        badge.setName(name);
        badge.setDescription(description);
        badge.setActive(true);
        badge.setPathImageColor("/badges/" + format(name) + "_color.png");
        badge.setPathImageBlackAndWhite("/badges/" + format(name) + "_bw.png");
        return badge;
    }

    private static String format(String name) {
        return name.toLowerCase().replace(" ", "_");
    }
}

