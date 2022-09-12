package fr.alexandreguiny.lastdayonearth.item

import fr.alexandreguiny.lastdayonearth.variable.Category
import java.awt.event.ActionEvent
import javax.swing.*

class ModifyDialog(private val parameter: Parameter) : JPanel() {

    private val naturalBox = JCheckBox("natural")
    private val comboCategories = JComboBox<Category>()

    init {
        val frame = JFrame()


        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        add(JLabel(parameter.iconLabel.icon))

        // naturalBox
            naturalBox.isSelected = parameter.isNatural
            add(naturalBox)
        // naturalBox


        for (category in fr.alexandreguiny.lastdayonearth.variable.Category::class.java.enumConstants) {
            comboCategories.addItem(category)
        }
        comboCategories.selectedItem = parameter.category
        comboCategories.maximumSize = comboCategories.preferredSize
        add(comboCategories)

        // button OK
        val button = JButton("OK")
        button.addActionListener { e: ActionEvent? ->
            setModif(parameter)
            frame.dispose()
        }
        add(button)
        // button OK

        frame.add(this)
        frame.setSize(500, 500)
        frame.isVisible = true
    }

    private fun setModif(parameter: Parameter) {
        parameter.isNatural = naturalBox.isSelected
        parameter.category = comboCategories.selectedItem as Category
    }

}