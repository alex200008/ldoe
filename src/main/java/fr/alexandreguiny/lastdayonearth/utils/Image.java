package fr.alexandreguiny.lastdayonearth.utils;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

public class Image extends JLabel {
    public static final HashMap<String, JLabel> images = new HashMap<>();
    private String name;

    public Image(File file) {
        try {
            var imageIcon = new ImageIcon(file.toURI().toURL());
            this.name = file.getName().replace(".png", "");
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
            this.setIcon(imageIcon);
            var textFrame = new TextFrame(name);
            this.addMouseListener(textFrame);
            this.addMouseMotionListener(textFrame);
            images.put(this.name, this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        init(new File("Images"));
    }

    private static void init(File file) {
        if (file.isFile()) {
            new Image(file);
        } else if (file.isDirectory()) {
            //noinspection ConstantConditions
            for (var child : file.listFiles()) {
                init(child);
            }
        }
    }

    public static JLabel get(String name) {
        var label = images.get(name);
        if (label != null)
            images.remove(name);
        return label;
    }
}
