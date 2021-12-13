class Day12 {
    companion object {
        const val START = "start"
        const val END = "end"
    }

    // 3485
    fun part1(): Int {
        return allPaths(buildGraph(), false).size
    }

    // 85062
    fun part2(): Int {
        return allPaths(buildGraph(), true).size
    }

    private fun buildGraph(): Graph<String> {
        val graph = Graph<String>()
        readLines(this).forEach {
            val path = it.split("-")
            graph.addEdge(path[0], path[1])
        }
        return graph
    }


    private fun allPaths(
        graph: Graph<String>,
        allowOneSmallCaveTwice: Boolean
    ): Set<List<String>> {
        val allPaths = mutableSetOf<List<String>>()
        allPathsRecursion(
            graph,
            START,
            mutableListOf(),
            mutableListOf(),
            allPaths,
            allowOneSmallCaveTwice
        )
        return allPaths
    }

    private fun allPathsRecursion(
        graph: Graph<String>,
        startNode: String,
        visited: MutableList<String>,
        path: MutableList<String>,
        allPaths: MutableSet<List<String>>,
        allowOneSmallCaveTwice: Boolean
    ) {
        val nextNodeCondition: (String) -> Boolean =
            { nextNode ->
                nextNode != START &&
                        if (allowOneSmallCaveTwice) {
                            !nextNode.isLowerCase() || !visited.contains(nextNode) || !smallCaveVisitedTwice(path)
                        } else {
                            !nextNode.isLowerCase() || !visited.contains(nextNode)
                        }
            }

        visited.add(startNode)
        path.add(startNode)
        if (startNode == END) {
            allPaths.add(path.toList())
        } else {
            graph.edges[startNode]
                ?.filter { nextNodeCondition(it) }
                ?.forEach { nextNode ->
                    allPathsRecursion(graph, nextNode, visited, path, allPaths, allowOneSmallCaveTwice)
                }
        }
        path.removeLast()
        visited.removeLast()
    }

    private fun smallCaveVisitedTwice(path: List<String>): Boolean {
        val smallCaves = path.filter { it.isLowerCase() }
        return smallCaves.distinct().size < smallCaves.size
    }
}