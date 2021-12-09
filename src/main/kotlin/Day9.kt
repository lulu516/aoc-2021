class Day9 {
    data class Point(val x: Int, val y: Int, val value: Int)

    private val input = readLines(this).map { it.toCharArray().map { c -> Character.getNumericValue(c) } }

    // 524
    fun part1(): Int {
        val graph = Graph<Point>()

        input.indices.forEach { x ->
            input[x].indices.forEach { y ->
                addEdge(x, y, graph)
            }
        }
        val lowPoints = graph.edges.filter { isLowPoint(it.key, it.value) }.map { it.key }

        return lowPoints.sumOf { it.value } + lowPoints.size
    }

    // 1235430
    fun part2(): Int {
        val graph = Graph<Point>()

        input.indices.forEach { x ->
            input[x].indices.forEach { y ->
                addEdge(x, y, graph)
            }
        }

        val basins: Map<Point, List<Point>> =
            graph.edges
                .filter { isLowPoint(it.key, it.value) }
                .map { it.key to graph.bfs(it.key) }
                .toMap()

        return basins.values.map { it.size }.sortedDescending().take(3).reduce { acc, i -> acc * i }
    }

    private fun addEdge(x: Int, y: Int, graph: Graph<Point>) {
        val xMax = input.size - 1
        val yMax = input.first().size - 1

        if (input[x][y] == 9) return

        val adjacentPoints = mutableListOf<Pair<Int, Int>>()
        if (y > 0) {
            adjacentPoints.add(Pair(x, y - 1))
        }
        if (y < yMax) {
            adjacentPoints.add(Pair(x, y + 1))
        }
        if (x > 0) {
            adjacentPoints.add(Pair(x - 1, y))
        }
        if (x < xMax) {
            adjacentPoints.add(Pair(x + 1, y))
        }

        adjacentPoints
            .map { Point(it.first, it.second, input[it.first][it.second]) }
            .filter { it.value != 9 }
            .forEach {
                graph.addEdge(Point(x, y, input[x][y]), it)
            }
    }

    private fun isLowPoint(current: Point, adjacentPoints: Set<Point>): Boolean {
        return current.value < adjacentPoints.map { it.value }.minByOrNull { it }!!
    }
}