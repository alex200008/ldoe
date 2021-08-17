package fr.alexandreguiny.lastdayonearth.variable;

public enum Category {
    TOOLS,
    CONSUMABLE,
    ARMOR,
    MATERIALS,
    OTHER;

    static public Category of(String category) {
        return switch (category) {
            case "TOOLS" -> TOOLS;
            case "CONSUMABLE" -> CONSUMABLE;
            case "ARMOR" -> ARMOR;
            case "MATERIALS" -> MATERIALS;
            default -> OTHER;
        };
    }
}
