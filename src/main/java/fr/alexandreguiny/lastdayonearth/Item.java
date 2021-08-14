package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static fr.alexandreguiny.lastdayonearth.Color.*;

public class Item extends JPanel{

    private static class ColorComboRenderer extends JPanel implements ListCellRenderer<Color> {
        protected Color m_c = Color.BLACK;

        public ColorComboRenderer()
        {
            super();
        }

        @Override
        public Component getListCellRendererComponent(JList list, Color color, int row, boolean sel, boolean hasFocus)
        {
            m_c = color;
            return this;
        }

        @Override
        public void paint(Graphics g)
        {
            setBackground(m_c.color);
            super.paint(g);
        }
    }

    public static final List<Item> ITEMS = new ArrayList<>();

    private static final HashMap<String, Parameter> map = new HashMap<>();
    static { // Init map
        var str = "";
        try {
            str = Files.readString(Path.of("Save.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var line : str.split("\r\n")) {
            var para = new Parameter(line);

            map.put(para.name, para);
        }
    } // Init map

    private final JComboBox<Color> comboBox = new JComboBox<>();
    private final JLabel icon = new JLabel();
    public final Parameter parameter;

    public Item(File path) {
        ITEMS.add(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        { // Init parameter
            Parameter parameter = map.get(path.getName());

            if (parameter == null) {
                this.parameter = new Parameter(path.getName(), GRAY, List.of(RED, YELLOW, ORANGE, GREEN));
                Item.map.put(this.parameter.name, this.parameter);
                return;
            } else {
                this.parameter = parameter;
            }
        } // Init parameter

        { // Init icon
            ImageIcon imageIcon = null; // load the image to a imageIcon
            try {
                imageIcon = new ImageIcon(path.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            assert imageIcon != null;
            Image image = imageIcon.getImage(); // transform it
            var len = 100;
            Image newImage = image.getScaledInstance(len, len, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newImage);

            this.icon.setIcon(imageIcon);

            this.add(this.icon);
        } // Init icon

        { // Init comboBox
            this.parameter.getAllColor().forEach(comboBox::addItem);
            this.comboBox.setRenderer(new ColorComboRenderer());
            this.comboBox.addActionListener(e -> this.setColor((Color) Objects.requireNonNull(comboBox.getSelectedItem())));

            this.add(this.comboBox);
        } // Init comboBox

        this.setColor(this.parameter.actualColor);
    }

    private void setColor(Color color) {
        this.setBackground(color.color);
        this.parameter.actualColor = color;
        this.comboBox.setSelectedItem(color);
        save();
    }

    public static void init(File file, JPanel panel) {
        if (file.isFile()) {
            panel.add(new Item(file));
            return;
        }
        for (var child : Objects.requireNonNull(file.listFiles())) {
            init(child, panel);
        }
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
            Files.writeString(file.toPath(), ITEMS.stream().map(Item::toString).reduce(String::concat).orElse(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return parameter.toString();
    }
}
