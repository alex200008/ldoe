package fr.alexandreguiny.lastdayonearth.variable;

import static fr.alexandreguiny.lastdayonearth.variable.Color.*;

public enum Category {
    TOOLS(GREEN, YELLOW, RED),
    CONSUMABLE(GREEN, YELLOW, RED),
    ARMOR(GREEN, YELLOW, RED),
    MATERIALS(GREEN, YELLOW, RED),
    GARAGE(GREEN, YELLOW, RED),
    OTHER(RED);

    private final Color[] colors;

    Category(Color... colors) {
        this.colors = colors;
    }

    public Color[] getColors() {
        return colors;
    }

    static public Category of(String category) {
        // TODO generic
        return switch (category) {
            case "TOOLS" -> TOOLS;
            case "CONSUMABLE" -> CONSUMABLE;
            case "ARMOR" -> ARMOR;
            case "MATERIALS" -> MATERIALS;
            case "GARAGE" -> GARAGE;
            default -> OTHER;
        };
    }
}
