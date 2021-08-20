package fr.alexandreguiny.lastdayonearth;

import fr.alexandreguiny.lastdayonearth.minipanel.Modif;
import fr.alexandreguiny.lastdayonearth.minipanel.Stat;
import fr.alexandreguiny.lastdayonearth.utils.Image;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class Frame extends JFrame {
    private final MyPanel<Stat> craftItems = new MyPanel<>(Stat::new) {
        @Override
        protected boolean filter(Parameter parameter) {
            return Craft.canCraft(parameter);
        }
    };
    private final MyPanel<Stat> allItems = new MyPanel<>(Stat::new) {
        @Override
        protected boolean filter(Parameter parameter) {
            return true;
        }
    };
    private final MyPanel<Stat> missCraft = new MyPanel<>(Stat::new) {
        @Override
        protected boolean filter(Parameter parameter) {
            return parameter.isNatural() && parameter.isMissing();
        }
    };
    private final MyPanel<Modif> modif = new MyPanel<>(Modif::new) {
        @Override
        protected boolean filter(Parameter parameter) {
            return true;
        }
    };

    public Frame(String title) throws HeadlessException {
        super(title);
        Image.init();
        Parameter.init();
        Craft.init();


        var menus = new JTabbedPane();
        menus.add("All", allItems);
        menus.add("Craft", craftItems);
        menus.add("Missing", missCraft);
        menus.add("Modif", modif);
        menus.addChangeListener(this::menu);
        this.add(menus);

        allItems.update();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension.width * 3 / 4, dimension.height * 3 / 4);
        this.setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Frame("Last Day On Earth");
    }

    public void menu(ChangeEvent e) {
        JTabbedPane obj = (JTabbedPane) e.getSource();
        MyPanel<?> myPanel = (MyPanel<?>) obj.getSelectedComponent();
        myPanel.update();

    }
}
