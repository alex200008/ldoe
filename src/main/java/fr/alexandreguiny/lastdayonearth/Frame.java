package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static void main(String[] args) {
        var frame = new Frame();

        var panel = new JPanel();
        panel.setLayout(new FlowLayout());


        panel.add(new Item("Copper_Ore.png"));

        frame.add(panel);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width * 3 / 4, dimension.height * 3 / 4);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
