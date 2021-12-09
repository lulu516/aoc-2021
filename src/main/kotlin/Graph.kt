data class Graph<T>(val edges: HashMap<T, HashSet<T>> = HashMap()) {

    fun addEdge(from: T, to: T): Graph<T> {
        edges
            .computeIfAbsent(from) { HashSet() }
            .add(to)

        edges
            .computeIfAbsent(to) { HashSet() }
            .add(from)

        return this
    }

    fun bfs(startNode: T, targetNode: T? = null): List<T> {
        val visited = mutableListOf<T>()
        val nextNodes = mutableListOf<T>()
        nextNodes.add(startNode)
        var currentNode: T?
        while (nextNodes.isNotEmpty()) {
            currentNode = nextNodes.removeFirst()
            visited.add(currentNode)
            if (currentNode == targetNode) return visited
            edges[currentNode]?.let { nextNodes.addAll(it) }
            nextNodes.removeAll(visited)
        }
        return visited
    }

    fun dfs(startNode: T, targetNode: T? = null): List<T> {
        val visited = mutableListOf<T>()
        dfsRecursion(startNode, targetNode, visited)
        return visited
    }

    private fun dfsRecursion(startNode: T, targetNode: T?, visited: MutableList<T>) {
        if (visited.contains(startNode) || targetNode in visited) return
        visited.add(startNode)
        if (startNode == targetNode) return

        edges[startNode]?.filterNot { nextNode -> visited.contains(nextNode) }
            ?.forEach { nextNode -> dfsRecursion(nextNode, targetNode, visited) }
    }
}