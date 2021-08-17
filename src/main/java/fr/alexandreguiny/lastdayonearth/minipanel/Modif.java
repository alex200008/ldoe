package fr.alexandreguiny.lastdayonearth.minipanel;

import fr.alexandreguiny.lastdayonearth.Parameter;
import fr.alexandreguiny.lastdayonearth.variable.Category;
import fr.alexandreguiny.lastdayonearth.variable.Color;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Modif extends MiniPanel {
    private final List<JCheckBox> colorBox = new ArrayList<>();
    private final JCheckBox naturalBox = new JCheckBox("natural");
    private final JComboBox<Category> comboCategories = new JComboBox<>();

    @Override
    public MiniPanel init(Parameter parameter) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel(parameter.getIcon().getIcon()));
        var button = new JButton("Modif");
        button.addActionListener(e -> modif(parameter));
        this.add(button);
        return this;
    }

    private void modif(final Parameter parameter) {
        var frame = new JFrame();
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        { // allColor
            for (var color : Color.class.getEnumConstants()) {
                var box = new JCheckBox(color.name());
                box.setBackground(color.color);
                panel.add(box);
                colorBox.add(box);
                if (parameter.getAllColor().contains(color)) {
                    box.setSelected(true);
                }
            }
        } // allColor

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
        parameter.setAllColor(colorBox.stream().filter(AbstractButton::isSelected).map(jCheckBox -> Color.of(jCheckBox.getText())).collect(Collectors.toSet()));
        parameter.setNatural(naturalBox.isSelected());
        parameter.setCategory((Category) comboCategories.getSelectedItem());
    }
}
