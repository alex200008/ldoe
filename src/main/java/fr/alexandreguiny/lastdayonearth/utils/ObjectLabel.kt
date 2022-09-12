package fr.alexandreguiny.lastdayonearth.utils

import fr.alexandreguiny.lastdayonearth.item.Parameter
import javax.swing.ImageIcon
import javax.swing.JLabel

class ObjectLabel(parameter: Parameter, imageIcon: ImageIcon? = Image.images[parameter.name]) : JLabel() {

    init {
        icon = imageIcon

        val textFrame = TextFrame(parameter)

        addMouseListener(textFrame)
        addMouseMotionListener(textFrame)
    }
}