package fr.alexandreguiny.lastdayonearth;

import fr.alexandreguiny.lastdayonearth.variable.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Craft {
    public static List<Craft> crafts = new ArrayList<>();

    public static void init() {
        var str = "";
        try {
            var craft = new File("Craft.txt");
            if (!craft.exists() && !craft.createNewFile())
                throw new RuntimeException("Failed to create file:'Craft.txt'");
            str = Files.readString(Path.of("Craft.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var line : str.split("\r\n")) {
            if (line.equals(""))
                continue;
            crafts.add(new Craft(line));
        }
    }

    public final Parameter obj;
    public final List<Parameter> other = new ArrayList<>();
    private boolean valid = true;

    public Craft(String line) {
        Craft.crafts.add(this);

        var list = line.split(",");
        this.obj = Parameter.find(list[0]);

        for (var name : list[1].split("\\.")) {
            var item = Parameter.find(name);
            if (item != null)
                this.other.add(item);
            else {
                System.out.println(name);
                valid = false;
            }
        }
    }

    public boolean canCraft() {
        if (obj == null || !valid)
            return false;
        return isMissing(obj) && !other.stream().map(Craft::isMissing).reduce((aBoolean, aBoolean2) -> aBoolean || aBoolean2).orElse(true);
    }

    public static boolean canCraft(Parameter parameter) {
        return Craft.crafts.stream()
                .filter(craft -> craft.obj.equals(parameter))
                .anyMatch(Craft::canCraft);
    }

    public static boolean isMissing(Parameter parameter) {
        return parameter.getActualColor().equals(Color.RED);
    }
}
