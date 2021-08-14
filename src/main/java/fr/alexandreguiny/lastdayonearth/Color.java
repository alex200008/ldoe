package fr.alexandreguiny.lastdayonearth;

import java.util.ArrayList;
import java.util.List;

public enum Color {
    RED(java.awt.Color.RED),
    YELLOW(java.awt.Color.YELLOW),
    GREEN(java.awt.Color.GREEN),
    ORANGE(java.awt.Color.ORANGE),
    CYAN(java.awt.Color.CYAN),
    BLACK(java.awt.Color.BLACK),
    GRAY(java.awt.Color.GRAY),
    ;

    public final java.awt.Color color;

    Color(java.awt.Color color) {
        this.color = color;
    }

    public static Color of(String str) {
        return switch (str) {
            case "RED" -> RED;
            case "YELLOW" -> YELLOW;
            case "GREEN" -> GREEN;
            case "ORANGE" -> ORANGE;
            case "CYAN" -> CYAN;
            default -> GRAY;
        };
    }

    public static List<Color> listOf(String[] list) {
        var colorList = new ArrayList<Color>();

        for (var str : list) {
            colorList.add(Color.of(str));
        }
        return colorList;
    }
}
