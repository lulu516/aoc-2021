class Day11 {
    data class Octopus(var value: Int, var flash: Boolean = false) {
        // For visualization
        override fun toString(): String {
            return value.toString()
        }
    }

    // 1741
    fun part1(): Int {
        val allOctopuses = getAllOctopuses()
        var counter = 0

        (1..100).forEach { _ ->
            allOctopuses.coordinateToValue.forEach { increase(it.key, allOctopuses) }
            counter += resetFlashedOctopus(allOctopuses)
        }

        allOctopuses.visualize()
        return counter
    }

    // 440
    fun part2(): Int {
        val allOctopuses = getAllOctopuses()
        var day = 0
        while (resetFlashedOctopus(allOctopuses) != allOctopuses.coordinateToValue.size) {
            day++
            allOctopuses.coordinateToValue.forEach { increase(it.key, allOctopuses) }
        }

        return day
    }

    /** Filters the octopus which have flashed, reset the flag and count. */
    private fun resetFlashedOctopus(allOctopus: Grid<Octopus>): Int {
        return allOctopus.coordinateToValue.values.filter { it.flash }.map { it.flash = false }.count()
    }


    private fun increase(coordinate: Grid.Coordinate, allOctopuses: Grid<Octopus>) {
        val octopus = allOctopuses.get(coordinate)
        if (octopus.flash) return
        if (octopus.value != 9) {
            octopus.value++
        } else {
            octopus.value = 0
            octopus.flash = true
            coordinate.getAdjacentCoordinates(true, allOctopuses.xMax(), allOctopuses.yMax())
                .filterNot { allOctopuses.get(it).flash }
                .forEach { increase(it, allOctopuses) }
        }
    }

    private fun getAllOctopuses(): Grid<Octopus> {
        val input = readLines(this)
        val allOctopuses = Grid<Octopus>()
        input.indices.forEach { y ->
            input[y].indices.forEach { x ->
                allOctopuses.addValue(x, y, Octopus(Character.getNumericValue(input[y][x])))
            }
        }
        return allOctopuses
    }
}