package fr.alexandreguiny.lastdayonearth.utils

import java.io.File
import java.net.MalformedURLException
import javax.swing.ImageIcon
import javax.swing.JLabel

class Image(file: File) : JLabel() {
    private val imageName: String

    init {
        if (!file.name.matches("[A-Z0-9][A-Za-z'0-9-]*(_[A-Z0-9][A-Za-z'0-9-]*)*\\.png".toRegex())) {
            Error("Bad format:'" + file.name + "'")
        }
        this.imageName = file.name.replace(".png", "")
        try {
            val imageIcon = ImageIcon(file.toURI().toURL())
            imageIcon.image = imageIcon.image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)
            this.icon = imageIcon
            val textFrame = TextFrame(imageName)
            addMouseListener(textFrame)
            addMouseMotionListener(textFrame)
            imageLabels[this.imageName] = this
            images[imageName] = imageIcon
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    companion object {
        val imageLabels = HashMap<String, JLabel>()
        val images = HashMap<String, ImageIcon>()
        fun init() {
            init(File("Images"))
        }

        private fun init(file: File) {
            if (file.isFile) {
                Image(file)
            } else if (file.isDirectory) {
                for (child in file.listFiles()!!) {
                    init(child)
                }
            }
        }

        operator fun get(name: String): JLabel {
            val label = imageLabels[name] ?: throw RuntimeException("Failed to find $name")
            imageLabels.remove(name)
            return label
        }
    }
}