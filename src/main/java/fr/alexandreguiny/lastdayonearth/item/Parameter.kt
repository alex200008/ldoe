package fr.alexandreguiny.lastdayonearth.item

import fr.alexandreguiny.lastdayonearth.utils.Error
import fr.alexandreguiny.lastdayonearth.utils.Image
import fr.alexandreguiny.lastdayonearth.utils.ObjectLabel
import fr.alexandreguiny.lastdayonearth.variable.Category
import fr.alexandreguiny.lastdayonearth.variable.Color
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import javax.swing.ImageIcon
import javax.swing.JLabel

class Parameter: Comparable<Parameter> {
    val name: String
    var iconLabel: JLabel

    var actualColor = Color.GRAY
        set(value) {
            field = value
            save()
        }
    var allColor = java.util.Set.of(Color.RED, Color.GREEN)
        set(value) {
            field = value
            save()
        }
    var isNatural = false
        set(value) {
            field = value
            save()
        }
    var category = Category.OTHER
        set(value) {
            field = value
            allColor = java.util.Set.of(*category.colors)
            save()
        }

    constructor(name: String, iconLabel: ImageIcon) {
        this.name = name.replace(".png", "")
        this.iconLabel = ObjectLabel(this, iconLabel)
        parameters.add(this)
    }

    constructor(line: String) {
        val para = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (para.size < 1) throw RuntimeException("Invalid argument :$line")

        name = para[0].replace(".png", "")
        if (para.size > 1) actualColor = Color.of(para[1])
        if (para.size > 2) // TODO delete me
            allColor = HashSet(
                Color.listOf(
                    para[2].split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                )
            )
        if (para.size > 3) isNatural = para[3] == "T"
        if (para.size > 4) {
            category = Category.of(para[4])
            allColor = setOf(*category.colors)
        }

        iconLabel = ObjectLabel(this)
        parameters.add(this)
    }

    override fun toString(): String {
        return """
            $name,$actualColor,${
            allColor.stream().map { obj: Color -> obj.toString() }.reduce { s: String, s2: String -> "$s.$s2" }
                .orElse("")
        },${if (isNatural) "T" else "F"},${category.name}
            
            """.trimIndent()
    }

    private fun save() {
        val file = File("Save.txt")
        if (!file.exists()) {
            try {
                if (file.createNewFile()) throw RuntimeException("Cannot Save")
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }
        }
        try {
            Files.writeString(file.toPath(), parameters.stream().map { obj: Parameter? -> obj.toString() }
                .reduce { obj: String, str: String -> obj + str }.orElse(""))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun compareTo(other: Parameter): Int {
        return name.lowercase().compareTo(other.name.lowercase())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Parameter

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    val isMissing: Boolean
        get() = actualColor == Color.RED || actualColor == Color.YELLOW
    val isEmpty: Boolean
        get() = actualColor == Color.RED

    companion object {
        @JvmField
        var parameters = HashSet<Parameter?>()
        val allLines = HashMap<String, String>()
        @JvmStatic
        fun init() {
            var str = ""
            try {
                val save = File("Save.txt")
                if (!save.exists() && !save.createNewFile()) throw RuntimeException("Failed to create 'Save.txt'")
                str = Files.readString(Path.of("Save.txt"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            for (line in str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                Parameter(line)
                val name = line.split(",")[0]
                allLines[name] = line
            }
            Image.images.forEach { (name: String, iconImage : ImageIcon) -> Parameter(name, iconImage) }
        }

        @JvmStatic
        fun find(name: String): Parameter? {
            val res = parameters.stream().filter { parameter: Parameter? -> parameter!!.name == name }
                .findFirst()
            if (res.isEmpty) Error("Can't find :'$name'")
            return res.orElse(null)
        }
    }


}