package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class Frame extends JFrame {
    public static void main(String[] args) {
        var frame = new Frame();

        var panel = new JPanel();
        panel.setLayout(new FlowLayout());

        /*
        panel.add(new Item("Images\\Adhesive.png"));
        panel.add(new Item("Images\\Animal_Rawhide.png"));
        panel.add(new Item("Images\\Bandages.png.png"));
        panel.add(new Item("Images\\Bolts.png"));
        panel.add(new Item("Images\\Bottle_Of_Water.png"));
        panel.add(new Item("Images\\CAC_Cards_Romeo.png"));
        panel.add(new Item("Images\\Canned_Food.png"));
        panel.add(new Item("Images\\Cigarettes.png"));
        panel.add(new Item("Images\\Combat_Gear_Coupon.png"));
        panel.add(new Item("Images\\Copper_Ore.png"));
        panel.add(new Item("Images\\Fur.png"));
        panel.add(new Item("Images\\Raw_Meat.png"));
        panel.add(new Item("Images\\Raw_Turkey.png"));
        panel.add(new Item("Images\\Rubber_Parts.png"));
        panel.add(new Item("Images\\Scrap_Metal.png"));
        panel.add(new Item("Images\\Seeds.png.png"));
        panel.add(new Item("Images\\Spring.png"));
        panel.add(new Item("Images\\Transistor.png"));
        */

        Arrays.stream(new File("Images").listFiles()).forEach(file -> panel.add(new Item(file)));



        frame.add(panel);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width * 3 / 4, dimension.height * 3 / 4);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
