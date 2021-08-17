package fr.alexandreguiny.lastdayonearth.utils;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

public class Image {
    private static final HashMap<String, JLabel> images = new HashMap<>();

    public static void init() {
        init(new File("Images"));
    }

    private static void init(File file) {
        if (file.isFile()) {
            try {
                var imageIcon = new ImageIcon(file.toURI().toURL());
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                var label = new JLabel();
                label.setIcon(imageIcon);
                images.put(file.getName(), label);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (file.isDirectory()) {
            //noinspection ConstantConditions
            for (var child : file.listFiles()) {
                init(child);
            }
        }
    }

    public static JLabel get(String name) {
        return images.get(name);
    }
}
