package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Frame extends JFrame {

    public Frame(String title) throws HeadlessException {
        super(title);
        var allItems = new JPanel();
        allItems.setLayout(new FlowLayout());


        Item.init(new File("Images"), allItems);
        this.add(allItems);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension.width * 3 / 4, dimension.height * 3 / 4);
        this.setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Frame("Last Day On Earth");
    }
}
