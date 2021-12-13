/**
 * Helper (I hope) class and functions
 */
data class Grid<T>(val coordinateToValue: HashMap<Coordinate, T> = HashMap()) {
    fun xMax() = coordinateToValue.keys.maxOf { it.x }

    fun yMax() = coordinateToValue.keys.maxOf { it.y }

    fun getSize() = coordinateToValue.size

    fun contains(x: Int, y: Int): Boolean = coordinateToValue.contains(Coordinate(x, y))

    fun get(coordinate: Coordinate): T = coordinateToValue[coordinate]!!

    fun get(x: Int, y: Int): T = coordinateToValue[Coordinate(x, y)]!!

    fun addValue(x: Int, y: Int, value: T) {
        coordinateToValue[Coordinate(x, y)] = value
    }

    fun visualize(default: String? = null) {
        (0..yMax()).forEach { y ->
            (0..xMax()).forEach { x ->
                val coordinate = Coordinate(x, y)
                if (default == null || coordinateToValue.contains(coordinate))
                    print(coordinateToValue[coordinate]!!)
                else print(default)
            }
            println()
        }
    }

    data class Coordinate(val x: Int, val y: Int) {
        fun getAdjacentCoordinates(includeDiagonal: Boolean, xMax: Int, yMax: Int): Set<Coordinate> {
            val adjacent = mutableSetOf<Coordinate>()
            if (y > 0) {
                adjacent.add(Coordinate(x, y - 1))
            }
            if (y < yMax) {
                adjacent.add(Coordinate(x, y + 1))
            }
            if (x > 0) {
                adjacent.add(Coordinate(x - 1, y))
            }
            if (x < xMax) {
                adjacent.add(Coordinate(x + 1, y))
            }
            if (includeDiagonal) {
                if (y > 0 && x > 0) {
                    adjacent.add(Coordinate(x - 1, y - 1))
                }

                if (y > 0 && x < xMax) {
                    adjacent.add(Coordinate(x + 1, y - 1))
                }

                if (y < yMax && x > 0) {
                    adjacent.add(Coordinate(x - 1, y + 1))
                }

                if (y < yMax && x < xMax) {
                    adjacent.add(Coordinate(x + 1, y + 1))
                }
            }
            return adjacent
        }
    }
}