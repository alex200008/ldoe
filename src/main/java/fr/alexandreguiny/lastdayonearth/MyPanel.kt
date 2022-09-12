package fr.alexandreguiny.lastdayonearth

import fr.alexandreguiny.lastdayonearth.item.Parameter
import fr.alexandreguiny.lastdayonearth.item.Stat
import fr.alexandreguiny.lastdayonearth.item.Stat.Companion.stats
import fr.alexandreguiny.lastdayonearth.variable.Category
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.util.TreeSet
import javax.swing.JPanel
import javax.swing.JTabbedPane
import javax.swing.event.ChangeEvent

// TODO add scroll
abstract class MyPanel : JPanel() {
    private val categories = JTabbedPane()
    private val parameterList = TreeSet<Stat>()
    private var filteredList : List<Stat> = parameterList.toList()
    private val all = JPanel()

    init {
        this.layout = BorderLayout()
        this.add(categories, BorderLayout.CENTER)
        all.layout = FlowLayout()
        categories.add(all, "All")
        categories.addChangeListener { e: ChangeEvent? -> subUpdate() }
        for (category in Category::class.java.enumConstants) {
            val panel = JPanel()
            panel.layout = FlowLayout()
            categories.add(panel, category.name)
        }
    }

    fun update() {
        filteredList = stats.filter { stat -> filter(stat.parameter) }
        filteredList.forEach { stat -> all.add(stat) }
        subUpdate()
    }

    fun subUpdate() {
        val panel = categories.selectedComponent as JPanel
        val title = categories.getTitleAt(categories.selectedIndex)
        panel.removeAll()
        if (title == "All") {
            for (stat in stats) {
                panel.add(stat)
            }
        } else {
            for (stat in stats) {
                if (stat.parameter.category == Category.of(title)) panel.add(stat)
            }
        }
    }

    protected abstract fun filter(item: Parameter): Boolean
}