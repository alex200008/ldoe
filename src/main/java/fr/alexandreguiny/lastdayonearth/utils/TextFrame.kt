package fr.alexandreguiny.lastdayonearth.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TextFrame implements MouseListener, MouseMotionListener {
    private final String name;
    private JFrame frame;

    public TextFrame(String name) {
        this.name = name;
    }

    private JFrame textFrame() {
        var frame = new JFrame();
        frame.setResizable(false);
        var text = new JTextArea(name);
        text.setEditable(false);

        //text.addMouseListener(this);
        //text.addMouseMotionListener(this);

        frame.add(text);
        frame.setSize(text.getPreferredSize());
        frame.setEnabled(false);
        //frame.setSize(100, 100);
        frame.setUndecorated(true);
        frame.setFocusable(false);
        frame.setAutoRequestFocus(false);
        frame.setVisible(true);
        return frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        frame = textFrame();
        var point = MouseInfo.getPointerInfo().getLocation();
        frame.setLocation(point.x + 10, point.y + 10);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        frame.dispose();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        var point = MouseInfo.getPointerInfo().getLocation();
        frame.setLocation(point.x + 10, point.y + 10);
    }
}
