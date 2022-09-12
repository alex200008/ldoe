package fr.alexandreguiny.lastdayonearth

import fr.alexandreguiny.lastdayonearth.item.Parameter
import fr.alexandreguiny.lastdayonearth.item.Parameter.Companion.find
import fr.alexandreguiny.lastdayonearth.variable.Color
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.*

class Recycler(line: String) {
    // FIXME unfinished
    enum class Type {
        CLOTHES, WEAPONS, UNKNOWN;

        companion object {
            fun of(name: String?): Type {
                return when (name) {
                    "CLOTHES" -> CLOTHES
                    "WEAPONS" -> WEAPONS
                    else -> UNKNOWN
                }
            }
        }
    }

    val type: Type
    val obj: Parameter
    val objs: MutableList<Parameter>

    init {
        val para = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        type = Type.of(para[0])
        obj = find(para[1])!!
        val objs: MutableList<Parameter> = ArrayList()
        for (strObj in para[2].split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            val otherObj = find(strObj)
            if (otherObj != null) objs.add(otherObj)
        }
        this.objs = objs
        val hash = HashSet(obj.allColor)
        hash.add(Color.ORANGE)
        obj.allColor = hash
        recyclers.add(this)
    }

    private val isUseFull: Boolean
        get() = obj.actualColor == Color.ORANGE && objs.any { parameter -> parameter.isMissing }

    companion object {
        var recyclers: MutableList<Recycler> = ArrayList()
        @Throws(IOException::class)
        fun init() {
            val file = File("Recycler.txt")
            if (!file.exists()) {
                if (!file.createNewFile()) System.err.println("Can't create new file 'Recycler.java'")
                return
            }
            for (line in Files.readAllLines(file.toPath())) {
                try {
                    Recycler(line)
                } catch (ignored: Exception) {
                }
            }
        }

        fun filter(parameter: Parameter?): Boolean {
            val recycler = recyclers.stream().filter { recycler1: Recycler -> recycler1.obj.equals(parameter) }
                .findFirst()
            return recycler.map { obj: Recycler -> obj.isUseFull }.orElse(false)
        }
    }
}