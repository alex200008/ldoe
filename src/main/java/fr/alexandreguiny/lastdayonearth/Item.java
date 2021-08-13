package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import static java.awt.Color.*;

public class Item extends JPanel{

    static class ColorComboRenderer extends JPanel implements ListCellRenderer
    {
        protected Color m_c = Color.black;

        public ColorComboRenderer()
        {
            super();
        }

        public Component getListCellRendererComponent(JList list, Object obj, int row, boolean sel, boolean hasFocus)
        {
            if (obj instanceof Color)
                m_c = (Color) obj;
            return this;
        }

        public void paint(Graphics g)
        {
            setBackground(m_c);
            super.paint(g);
        }
    }

    static private final HashMap<String, String> map = new HashMap<>() {
        @Override
        public String toString() {
            class MyString {
                public String str = "";
                public void add(String other) {
                    str += other;
                }
            }
            var str = new MyString();
            this.forEach((s, s2) -> {
                str.add(s + " " + s2 + "\r\n");
            });
            return str.str;
        }
    };

    static {
        var str = "";
        try {
            str = Files.readString(Path.of("Save.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var s : str.split("\r\n")) {
            var kk = s.split(" ");
            map.put(kk[0], kk[1]);
        }
    }

    private String name;
    private final JComboBox<Color> jCmbCouleur = new JComboBox<>();

    public Item(File path) {
        this.name = path.getName();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        var item = new JLabel();

        ImageIcon imageIcon = null; // load the image to a imageIcon
        try {
            imageIcon = new ImageIcon(path.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = imageIcon.getImage(); // transform it
        var len = 100;
        Image newimg = image.getScaledInstance(len, imageIcon.getIconHeight() * len / imageIcon.getIconWidth(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);

        item.setIcon(imageIcon);


        jCmbCouleur.addItem(RED);
        jCmbCouleur.addItem(YELLOW);
        jCmbCouleur.addItem(ORANGE);
        jCmbCouleur.addItem(GREEN);
        jCmbCouleur.setRenderer(new ColorComboRenderer());

        jCmbCouleur.addActionListener(e -> this.setColor((Color) jCmbCouleur.getSelectedItem()));

        this.add(item);
        this.add(jCmbCouleur);

        var cc = map.get(this.name);

        if (cc == null) {
            System.out.println(this.name);
            return;
        }

        switch (cc) {
            case "RED" -> this.setColor(RED);
            case "YELLOW" -> this.setColor(YELLOW);
            case "ORANGE" -> this.setColor(ORANGE);
            case "GREEN" -> this.setColor(GREEN);
        }
    }

    private void setColor(Color color) {
        this.setBackground(color);
        map.put(this.name, colorToString(color));
        new File("Save.txt");
        try {
            Files.writeString(Path.of("Save.txt"), map.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        jCmbCouleur.setSelectedItem(color);
    }

    static private String colorToString(Color color) {
        if (color.equals(RED))
            return "RED";
        if (color.equals(YELLOW))
            return "YELLOW";
        if (color.equals(ORANGE))
            return "ORANGE";
        if (color.equals(GREEN))
            return "GREEN";
        else
            return "";
    }

}
