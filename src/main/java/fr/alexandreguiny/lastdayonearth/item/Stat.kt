package fr.alexandreguiny.lastdayonearth.item

import fr.alexandreguiny.lastdayonearth.variable.Color
import java.awt.Component
import java.awt.Graphics
import java.util.*
import javax.swing.*

class Stat private constructor(val parameter: Parameter) : JPanel(), Comparable<Stat> {

    private class ColorComboRenderer : JPanel(), ListCellRenderer<Color> {
        private var m_c = Color.BLACK
        override fun getListCellRendererComponent(
            list: JList<out Color>?,
            color: Color,
            row: Int,
            sel: Boolean,
            hasFocus: Boolean
        ): Component {
            m_c = color
            return this
        }

        override fun paint(g: Graphics) {
            background = m_c.color
            super.paint(g)
        }
    }
    private val comboBox: JComboBox<Color> = JComboBox<Color>()


    private fun setColor(color: Color?) {
        if (color == null) return
        background = color.color
        parameter.actualColor = color
        comboBox.selectedItem = color
    }

    companion object {
        val stats : SortedSet<Stat> = TreeSet()
            get() {

                if (field.isEmpty())
                    for (parameter in Parameter.parameters)
                        field.add(Stat(parameter))

                return field
            }
    }

    override fun compareTo(other: Stat): Int {
        return parameter.compareTo(other.parameter)
    }

    init {
        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)
        this.add(parameter.iconLabel)
        run {
            parameter.allColor.forEach { item: Color -> comboBox.addItem(item) }
            comboBox.renderer = ColorComboRenderer()
            comboBox.addActionListener { this.setColor(comboBox.selectedItem as Color) }
            this.add(comboBox)
            setColor(parameter.actualColor)
        }
    }


}