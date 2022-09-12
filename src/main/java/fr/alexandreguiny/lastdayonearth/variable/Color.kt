package fr.alexandreguiny.lastdayonearth.variable

enum class Color(val color: java.awt.Color) {
    RED(java.awt.Color.RED),
    YELLOW(java.awt.Color.YELLOW),
    GREEN(java.awt.Color.GREEN),
    ORANGE(java.awt.Color.ORANGE),
    CYAN(java.awt.Color.CYAN),
    BLACK(java.awt.Color.BLACK),
    GRAY(java.awt.Color.GRAY);

    companion object {
        fun of(str: String?): Color {
            return when (str) {
                "RED" -> RED
                "YELLOW" -> YELLOW
                "GREEN" -> GREEN
                "ORANGE" -> ORANGE
                "CYAN" -> CYAN
                else -> GRAY
            }
        }

        fun listOf(list: Array<String>): List<Color> {
            val colorList = ArrayList<Color>()
            for (str in list) {
                colorList.add(of(str))
            }
            return colorList
        }
    }
}