package edu.dosw.rideci.domain.badge;

import edu.dosw.rideci.domain.model.Badge;


public class BadgeFactory {

    public static Badge build(String name, String description) {
        return Badge.builder()
                .name(name)
                .description(description)
                .isActive(true)
                .pathImageColor("/badges/" + format(name) + "_color.png")
                .pathImageBlackAndWhite("/badges/" + format(name) + "_bw.png")
                .build();
    }

    private static String format(String name) {
        return name.toLowerCase().replace(" ", "_");
    }
}