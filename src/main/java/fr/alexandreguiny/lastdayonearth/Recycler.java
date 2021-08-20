package fr.alexandreguiny.lastdayonearth;

import fr.alexandreguiny.lastdayonearth.variable.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static fr.alexandreguiny.lastdayonearth.variable.Color.ORANGE;

public class Recycler {
    // FIXME unfinished
    enum Type {
        CLOTHES,
        UNKNOWN;

        static public Type of(String name) {
            return switch (name) {
                case "CLOTHES" -> CLOTHES;
                default -> UNKNOWN;
            };
        }
    }

    public static List<Recycler> recyclers = new ArrayList<>();

    public final Type type;
    public final Parameter obj;
    public final Parameter[] objs;

    public Recycler(String line) {
        var para = line.split(",");
        type = Type.of(para[0]);
        obj = Parameter.find(para[1]);

        final List<Parameter> objs = new ArrayList<>();
        for (var strObj : para[2].split("\\.")) {
            objs.add(Parameter.find(strObj));
        }

        this.objs = objs.toArray(Parameter[]::new);

        var hash = new HashSet<>(obj.getAllColor());
        hash.add(ORANGE);
        obj.setAllColor(hash);
        recyclers.add(this);
    }

    public static void init() throws IOException {
        var file = new File("Recycler.txt");

        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        for (var line : Files.readAllLines(file.toPath())) {
            new Recycler(line);
        }

    }

    static public boolean filter(Parameter parameter) {
        var recycler = recyclers.stream().filter(recycler1 -> recycler1.obj.equals(parameter)).findFirst();
        return recycler.map(Recycler::isUseFull).orElse(false);
    }


    private boolean isUseFull() {
        return obj.getActualColor().equals(ORANGE) && Arrays.stream(objs).anyMatch(Parameter::isMissing);
    }
}
