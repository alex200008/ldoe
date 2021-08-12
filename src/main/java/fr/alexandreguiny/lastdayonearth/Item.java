package fr.alexandreguiny.lastdayonearth;

import javax.swing.*;
import java.awt.*;

public class Item extends JPanel{
    static class ColorComboRenderer extends JPanel implements ListCellRenderer
    {
        protected Color m_c = Color.black;

        public ColorComboRenderer()
        {
            super();
        }

        public Component getListCellRendererComponent(JList list, Object obj, int row, boolean sel, boolean hasFocus)
        {
            if (obj instanceof Color)
                m_c = (Color) obj;
            return this;
        }

        public void paint(Graphics g)
        {
            setBackground(m_c);
            super.paint(g);
        }
    }

    public Item(String path) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        var item = new JLabel();
        var url = this.getClass().getClassLoader().getResource(path);
        assert url != null;

        ImageIcon imageIcon = new ImageIcon(url); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        var len = 100;
        Image newimg = image.getScaledInstance(len, imageIcon.getIconHeight() * len / imageIcon.getIconWidth(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);

        item.setIcon(imageIcon);


        JComboBox<Color> jCmbCouleur = new JComboBox<>();
        jCmbCouleur.addItem(Color.RED);
        jCmbCouleur.addItem(Color.YELLOW);
        jCmbCouleur.addItem(Color.GREEN);
        jCmbCouleur.setRenderer(new ColorComboRenderer());

        jCmbCouleur.addActionListener(e -> this.setBackground((Color) jCmbCouleur.getSelectedItem()));

        this.add(item);
        this.add(jCmbCouleur);

    }



}
