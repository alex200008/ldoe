package fr.alexandreguiny.lastdayonearth.utils

import fr.alexandreguiny.lastdayonearth.item.Parameter
import fr.alexandreguiny.lastdayonearth.item.ModifyDialog
import java.awt.MouseInfo
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import javax.swing.JFrame
import javax.swing.JTextArea

class TextFrame(private val name: String) : MouseListener, MouseMotionListener {

    var parameter : Parameter? = null

    constructor(parameter: Parameter) : this(parameter.name) {
        this.parameter = parameter
    }

    private var frame: JFrame? = null
    private fun textFrame(): JFrame {
        val frame = JFrame()
        frame.isResizable = false
        val text = JTextArea(name)
        text.isEditable = false
        frame.add(text)
        frame.size = text.preferredSize
        frame.isEnabled = false
        //frame.setSize(100, 100);
        frame.isUndecorated = true
        frame.isFocusable = false
        frame.isAutoRequestFocus = false
        frame.isVisible = true
        return frame
    }

    override fun mouseClicked(e: MouseEvent) {
        parameter?.let { ModifyDialog(it) }
    }


    override fun mousePressed(e: MouseEvent) {}
    override fun mouseReleased(e: MouseEvent) {}
    override fun mouseEntered(e: MouseEvent) {
        frame = textFrame()
        val point = MouseInfo.getPointerInfo().location
        frame!!.setLocation(point.x + 10, point.y + 10)
    }

    override fun mouseExited(e: MouseEvent) {
        frame!!.dispose()
    }

    override fun mouseDragged(e: MouseEvent) {}
    override fun mouseMoved(e: MouseEvent) {
        val point = MouseInfo.getPointerInfo().location
        frame!!.setLocation(point.x + 10, point.y + 10)
    }
}