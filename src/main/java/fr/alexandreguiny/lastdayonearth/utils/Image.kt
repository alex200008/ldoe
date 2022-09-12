package fr.alexandreguiny.lastdayonearth.utils

import java.io.File
import java.net.MalformedURLException
import javax.swing.ImageIcon

class Image(file: File) : ImageIcon() {
    init {
        if (!file.name.matches("[A-Z0-9][A-Za-z'0-9-]*(_[A-Z0-9][A-Za-z'0-9-]*)*\\.png".toRegex())) {
            Error("Bad format:'" + file.name + "'")
        }
        val name = file.name.replace(".png", "")
        try {

            image = ImageIcon(file.toURI().toURL()).image
            if (iconHeight != 100 || iconWidth != 100)
                image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)
            images[name] = this
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    companion object {
        private var alreadyInit = false
        private val images: HashMap<String, ImageIcon> = HashMap()
            get() {
                if (!alreadyInit) {
                    alreadyInit = true
                    read(File("Images"))
                }
                return field
            }

        private fun read(file: File) {
            if (file.isFile) {
                Image(file)
            } else if (file.isDirectory) {
                for (child in file.listFiles()!!) {
                    read(child)
                }
            }
        }

        operator fun get(name : String) : ImageIcon? {
            val icon = images[name]
            images.remove(name)
            return icon
        }
    }
}