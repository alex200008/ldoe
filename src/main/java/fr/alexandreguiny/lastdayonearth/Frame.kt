package fr.alexandreguiny.lastdayonearth

import fr.alexandreguiny.lastdayonearth.Craft.Companion.canCraft
import fr.alexandreguiny.lastdayonearth.item.Parameter
import java.awt.Toolkit
import java.io.IOException
import javax.swing.JFrame
import javax.swing.JTabbedPane
import javax.swing.event.ChangeEvent

// TODO -optimiser la sauvegarde
// TODO add Timer
class Frame(title: String?) : JFrame(title) {
    private val craftItems: MyPanel = object : MyPanel() {
        override fun filter(item: Parameter): Boolean {
            return canCraft(item)
        }
    }
    private val allItems: MyPanel = object : MyPanel() {
        override fun filter(item: Parameter): Boolean {
            return true
        }
    }
    private val missCraft: MyPanel = object : MyPanel() {
        override fun filter(item: Parameter): Boolean {
            return item.isNatural && item.isMissing
        }
    }
    private val recycler: MyPanel = object : MyPanel() {
        override fun filter(item: Parameter): Boolean {
            return Recycler.filter(item)
        }
    }

    init {
        Parameter.init()
        Craft.init()
        Recycler.init()
        val menus = JTabbedPane()
        menus.add("All", allItems)
        menus.add("Craft", craftItems)
        menus.add("Missing", missCraft)
        menus.add("Recycler", recycler)
        menus.addChangeListener { e: ChangeEvent -> menu(e) }
        this.add(menus)
        allItems.update()
        val dimension = Toolkit.getDefaultToolkit().screenSize
        this.setSize(dimension.width * 3 / 4, dimension.height * 3 / 4)
        this.isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    fun menu(e: ChangeEvent) {
        val obj = e.source as JTabbedPane
        val myPanel = obj.selectedComponent as MyPanel
        myPanel.update()
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            Frame("Last Day On Earth")
        }
    }
}