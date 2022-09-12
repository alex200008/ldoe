package fr.alexandreguiny.lastdayonearth.utils

import javax.swing.JFrame
import javax.swing.JOptionPane

class Error(message: String?) : JFrame() {
    init {
        JOptionPane.showMessageDialog(this, message)
    }
}