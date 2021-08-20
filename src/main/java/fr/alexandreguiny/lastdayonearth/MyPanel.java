package fr.alexandreguiny.lastdayonearth;

import fr.alexandreguiny.lastdayonearth.minipanel.MiniPanel;
import fr.alexandreguiny.lastdayonearth.variable.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Supplier;

// TODO add scroll
public abstract class  MyPanel <PANEL extends MiniPanel> extends JPanel {
    private JTabbedPane categories = new JTabbedPane();
    private ArrayList<Parameter> parameterList = new ArrayList<>();
    private JPanel all = new JPanel();
    private Supplier<PANEL> constructor;

    public MyPanel(Supplier<PANEL> constructor) {
        this.constructor = constructor;
        this.setLayout(new BorderLayout());
        this.add(categories, BorderLayout.CENTER);
        all.setLayout(new FlowLayout());
        categories.add(all, "All");
        categories.addChangeListener(e -> subUpdate());
        for (var category : Category.class.getEnumConstants()) {
            var panel = new JPanel();
            panel.setLayout(new FlowLayout());
            categories.add(panel, category.name());
        }
    }

    public void update() {
        parameterList.clear();
        for (var parameter : Parameter.parameters) {
            if (filter(parameter)) {
                parameterList.add(parameter);
                all.add(map(parameter));
            }
        }
        subUpdate();
    }

    public void subUpdate() {
        var panel = (JPanel) categories.getSelectedComponent();
        var title = categories.getTitleAt(categories.getSelectedIndex());
        if (title.equals("All")) {
            panel.removeAll();
            for(var parameter : parameterList) {
                panel.add(map(parameter));
            }
        } else {
            panel.removeAll();
            for(var parameter : parameterList) {
                if (parameter.getCategory().equals(Category.of(title)))
                    panel.add(map(parameter));
            }
        }
    }

    protected MiniPanel map(Parameter parameter) {
        return constructor.get().init(parameter);
    }

    protected abstract boolean filter(Parameter item);
}
