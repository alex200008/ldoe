package fr.alexandreguiny.lastdayonearth;

public enum Color {
    RED(java.awt.Color.RED),
    YELLOW(java.awt.Color.YELLOW),
    GREEN(java.awt.Color.GREEN),
    ORANGE(java.awt.Color.ORANGE),
    CYAN(java.awt.Color.CYAN),
    BLACK(java.awt.Color.BLACK),
    ;

    public final java.awt.Color color;

    Color(java.awt.Color color) {
        this.color = color;
    }
}
