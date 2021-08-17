package fr.alexandreguiny.lastdayonearth.minipanel;

import fr.alexandreguiny.lastdayonearth.Parameter;
import fr.alexandreguiny.lastdayonearth.variable.Color;

import javax.swing.*;
import java.awt.*;

public class Stat extends MiniPanel {

    private static class ColorComboRenderer extends JPanel implements ListCellRenderer<Color> {
        protected Color m_c = Color.BLACK;

        public ColorComboRenderer() {
            super();
        }

        @Override
        public Component getListCellRendererComponent(JList list, Color color, int row, boolean sel, boolean hasFocus) {
            m_c = color;
            return this;
        }

        @Override
        public void paint(Graphics g) {
            setBackground(m_c.color);
            super.paint(g);
        }
    }
    private Parameter parameter;
    private JComboBox<Color> comboBox = new JComboBox<>();

    @Override
    public MiniPanel init(Parameter parameter) {
        this.parameter = parameter;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(parameter.getIcon());
        {
            parameter.getAllColor().forEach(comboBox::addItem);
            comboBox.setRenderer(new ColorComboRenderer());
            comboBox.addActionListener(e -> this.setColor((Color) comboBox.getSelectedItem()));
            this.add(comboBox);
        }

        this.setColor(parameter.getActualColor());
        return this;
    }

    private void setColor(Color color) {
        if (color == null)
            return;
        this.setBackground(color.color);
        parameter.setActualColor(color);
        comboBox.setSelectedItem(color);
    }
}
