package fr.alexandreguiny.lastdayonearth.minipanel;

import fr.alexandreguiny.lastdayonearth.Parameter;
import fr.alexandreguiny.lastdayonearth.variable.Category;

import javax.swing.*;

public class Modif extends MiniPanel {
    private final JCheckBox naturalBox = new JCheckBox("natural");
    private final JComboBox<Category> comboCategories = new JComboBox<>();

    @Override
    public MiniPanel init(Parameter parameter) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(parameter.getIcon());
        var button = new JButton("Modif");
        button.addActionListener(e -> modif(parameter));
        this.add(button);
        return this;
    }

    private void modif(final Parameter parameter) {
        var frame = new JFrame();
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel(parameter.getIcon().getIcon()));

        { // naturalBox
            naturalBox.setSelected(parameter.isNatural());
            panel.add(naturalBox);
        } // naturalBox

        { // categories
            var xpanel = new JPanel();
            for (var category : Category.class.getEnumConstants()) {
                comboCategories.addItem(category);
            }
            comboCategories.setSelectedItem(parameter.getCategory());
            comboCategories.setMaximumSize(comboCategories.getPreferredSize());
            panel.add(comboCategories);
        } // categories

        { // button OK
            var button = new JButton("OK");
            button.addActionListener(e -> {
                setModif(parameter);
                frame.dispose();
            });
            panel.add(button);
        } // button OK

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private void setModif(final Parameter parameter) {
        parameter.setNatural(naturalBox.isSelected());
        parameter.setCategory((Category) comboCategories.getSelectedItem());
    }
}
