package fr.alexandreguiny.lastdayonearth

import fr.alexandreguiny.lastdayonearth.item.Parameter
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

class Craft(line: String) {
    val obj: Parameter?
    val other: MutableList<Parameter> = ArrayList()
    private var valid = true

    init {
        crafts.add(this)
        val list = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        obj = Parameter.find(list[0])
        for (name in list[1].split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            val item = Parameter.find(name)
            if (item != null) other.add(item) else {
                println(name)
                valid = false
            }
        }
    }

    fun canCraft(): Boolean {
        return if (obj == null || !valid) false else obj.isMissing && !other.stream()
            .map { obj: Parameter -> obj.isEmpty }
            .reduce { aBoolean: Boolean, aBoolean2: Boolean -> aBoolean || aBoolean2 }.orElse(true)
    }

    companion object {
        var crafts: MutableList<Craft> = ArrayList()
        @JvmStatic
        fun init() {
            var str = ""
            try {
                val craft = File("Craft.txt")
                if (!craft.exists() && !craft.createNewFile()) throw RuntimeException("Failed to create file:'Craft.txt'")
                str = Files.readString(Path.of("Craft.txt"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            for (line in str.split("\r\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                if (line == "") continue
                crafts.add(Craft(line))
            }
        }

        @JvmStatic
        fun canCraft(parameter: Parameter): Boolean {
            return crafts.stream()
                .filter { craft: Craft -> craft.obj == parameter }
                .anyMatch { obj: Craft -> obj.canCraft() }
        }
    }
}