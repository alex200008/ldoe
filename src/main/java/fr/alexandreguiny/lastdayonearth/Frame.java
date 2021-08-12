package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static void main(String[] args) {
        var frame = new Frame();

        var panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new Item("Adhesive.png"));
        panel.add(new Item("Animal_Rawhide.png"));
        panel.add(new Item("Bandages.png.png"));
        panel.add(new Item("Bolts.png"));
        panel.add(new Item("Bottle_Of_Water.png"));
        panel.add(new Item("CAC_Cards_Romeo.png"));
        panel.add(new Item("Canned_Food.png"));
        panel.add(new Item("Cigarettes.png"));
        panel.add(new Item("Combat_Gear_Coupon.png"));
        panel.add(new Item("Copper_Ore.png"));
        panel.add(new Item("Fur.png"));
        panel.add(new Item("Raw_Meat.png"));
        panel.add(new Item("Raw_Turkey.png"));
        panel.add(new Item("Rubber_Parts.png"));
        panel.add(new Item("Scrap_Metal.png"));
        panel.add(new Item("Seeds.png.png"));
        panel.add(new Item("Spring.png"));
        panel.add(new Item("Transistor.png"));


        frame.add(panel);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width * 3 / 4, dimension.height * 3 / 4);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
