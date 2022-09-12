package fr.alexandreguiny.lastdayonearth.variable

import fr.alexandreguiny.lastdayonearth.variable.Color.*

enum class Category(vararg colors: Color) {
    TOOLS(GREEN, YELLOW, RED),
    CONSUMABLE(GREEN, YELLOW, RED),
    ARMOR(GREEN, YELLOW, RED),
    MATERIALS(GREEN, YELLOW, RED),
    GARAGE(GREEN, YELLOW, RED),
    OTHER(RED);

    val colors: Array<out Color>

    init {
        this.colors = colors
    }

    companion object {
        fun of(category: String): Category {
            // TODO generic
            return when (category) {
                "TOOLS" -> TOOLS
                "CONSUMABLE" -> CONSUMABLE
                "ARMOR" -> ARMOR
                "MATERIALS" -> MATERIALS
                "GARAGE" -> GARAGE
                else -> OTHER
            }
        }
    }
}