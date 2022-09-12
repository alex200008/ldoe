package fr.alexandreguiny.lastdayonearth.utils

import fr.alexandreguiny.lastdayonearth.item.Parameter
import javax.swing.JLabel

class ObjectLabel(parameter: Parameter) : JLabel() {

    init {
        icon = Image[parameter.name]

        alignmentX = CENTER_ALIGNMENT

        val textFrame = TextFrame(parameter)

        addMouseListener(textFrame)
        addMouseMotionListener(textFrame)
    }
}