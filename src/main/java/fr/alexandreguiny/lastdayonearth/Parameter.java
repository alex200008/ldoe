package fr.alexandreguiny.lastdayonearth;

import fr.alexandreguiny.lastdayonearth.utils.Image;
import fr.alexandreguiny.lastdayonearth.variable.Category;
import fr.alexandreguiny.lastdayonearth.variable.Color;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static fr.alexandreguiny.lastdayonearth.variable.Category.OTHER;
import static fr.alexandreguiny.lastdayonearth.variable.Color.*;

public class Parameter {
    public final String name;
    private Color actualColor = Color.GRAY;
    private Set<Color> allColor = Set.of(RED, Color.GREEN);
    private boolean natural = false;
    private Category category = OTHER;
    private final JLabel icon;
    public static ArrayList<Parameter> parameters = new ArrayList<>();

    public Parameter(String name, JLabel icon) {
        this.name = name.replace(".png", "");
        this.icon = icon;
        parameters.add(this);
    }

    public static void init() {
        var str = "";
        try {
            var save = new File("Save.txt");
            if (!save.exists() && !save.createNewFile())
                throw new RuntimeException("Failed to create 'Save.txt'");
            str = Files.readString(Path.of("Save.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var line : str.split("\r\n")) {
            new Parameter(line);

        }
        Image.images.forEach(Parameter::new);
    }

    public Parameter(String line) {
        var para = line.split(",");
        if (para.length < 1)
            throw new RuntimeException("Invalid argument :" + line);

        this.name = para[0].replace(".png", "");
        this.icon = Image.get(this.name);
        if (this.icon == null)
            throw new RuntimeException("Failed to find " + this.name);
        if (para.length > 1)
            this.actualColor = Color.of(para[1]);
        if (para.length > 2)
            this.allColor = new HashSet<>(Color.listOf(para[2].split("\\.")));
        if (para.length > 3)
            natural = para[3].equals("T");
        if (para.length > 4) {
            category = Category.of(para[4]);
            allColor = Set.of(category.getColors());
        }
        parameters.add(this);
    }

    public static Parameter find(String name) {
        return parameters.stream().filter(parameter -> parameter.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name + ',' +
                actualColor + "," +
                allColor.stream().map(Enum::toString).reduce((s, s2) -> s + "." + s2).orElse("") + "," +
                ((natural) ? "T" : "F") + "," +
                category.name() +
                "\r\n";
    }

    public Color getActualColor() {
        return actualColor;
    }

    public void setActualColor(Color actualColor) {
        this.actualColor = actualColor;
        save();
    }

    public Set<Color> getAllColor() {
        return allColor;
    }

    public void setAllColor(Set<Color> allColor) {
        this.allColor = allColor;
        save();
    }

    public boolean isNatural() {
        return natural;
    }

    public void setNatural(boolean natural) {
        this.natural = natural;
        save();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.allColor = Set.of(category.getColors());
        save();
    }

    public JLabel getIcon() {
        return icon;
    }

    private void save() {
        var file = new File("Save.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile())
                    throw new RuntimeException("Cannot Save");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            Files.writeString(file.toPath(), parameters.stream().map(Parameter::toString).reduce(String::concat).orElse(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isMissing() {
        return actualColor.equals(RED) || actualColor.equals(YELLOW);
    }

    public boolean isEmpty() {
        return actualColor.equals(RED);
    }




}
