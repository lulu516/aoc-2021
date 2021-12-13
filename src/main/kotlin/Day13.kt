class Day13 {
    data class Instruction(val foldX: Boolean, val line: Int)

    // 678
    fun part1(): Int {
        val grid = getGrid()
        val firstFold = fold(getInstructions()[0], grid)
        return firstFold.getSize()
    }

    // ECFHLHZF
    fun part2() {
        var grid = getGrid()
        val foldInstructions = getInstructions()

        foldInstructions.forEach { grid = fold(it, grid) }
        grid.visualize(".")
    }

    private fun getGrid(): Grid<String> {
        val grid = Grid<String>()
        readLines(this).takeWhile { it.isNotEmpty() }.map {
            val i = it.split(",")
            grid.addValue(i[0].toInt(), i[1].toInt(), "#")
        }
        return grid
    }

    private fun getInstructions(): List<Instruction> {
        val instructionRegex = """fold along (x|y)=(\d+)""".toRegex()

        return readLines(this).takeLastWhile { it.isNotEmpty() }.map {
            val (direction, num) = instructionRegex.find(it)!!.destructured
            Instruction(direction == "x", num.toInt())
        }
    }

    private fun fold(instruction: Instruction, grid: Grid<String>): Grid<String> {
        val afterFold = Grid<String>()
        if (instruction.foldX) {
            (0 until instruction.line).forEach { x ->
                (0..grid.yMax()).forEach { y ->
                    // 2 * instruction.line - x: e.g. If 5 rows (0 to 4) and fold on row 2,
                    // row 3 will become 2 - (3 - 2) = 2 * 2 - 3 = 1; same for folding left.
                    if (grid.contains(x, y) || grid.contains(2 * instruction.line - x, y)) {
                        afterFold.addValue(x, y, "#")
                    }

                }
            }
        } else {
            (0 until instruction.line).forEach { y ->
                (0..grid.xMax()).forEach { x ->
                    if (grid.contains(x, y) || grid.contains(x, 2 * instruction.line - y)) {
                        afterFold.addValue(x, y, "#")
                    }
                }
            }
        }
        return afterFold
    }
}