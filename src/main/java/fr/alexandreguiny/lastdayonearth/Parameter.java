package fr.alexandreguiny.lastdayonearth;

import java.util.List;

public class Parameter {
    public final String name;
    public Color actualColor;
    private final List<Color> allColor;

    public Parameter(String name, Color actualColor, List<Color> allColor) {
        this.name = name;
        this.actualColor = actualColor;
        this.allColor = allColor;
    }

    public Parameter(String line) {
        var para = line.split(",");
        if (para.length != 3)
            throw new RuntimeException("Invalid argument :" + line);

        this.name = para[0];
        this.actualColor = Color.of(para[1]);
        this.allColor = Color.listOf(para[2].split("\\."));
    }

    public List<Color> getAllColor() {
        return allColor;
    }

    @Override
    public String toString() {
        return name + ',' + actualColor + "," + allColor.stream().map(Enum::toString).reduce((s, s2) -> s + "." + s2).orElse("") + "\r\n";
    }
}
