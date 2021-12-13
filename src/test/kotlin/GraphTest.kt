import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GraphTest {

    @Test
    fun search() {
        val graph = Graph<Int>()

        graph.addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(1, 3)
            .addEdge(2, 3)
            .addEdge(2, 6)
            .addEdge(3, 4)
            .addEdge(3, 5)
            .addEdge(5, 6)

        assertEquals(listOf(0, 1, 2, 3, 6, 4, 5), graph.bfs(0))
        assertEquals(listOf(0, 1, 2, 3, 6), graph.bfs(0, 6))
        assertEquals(listOf(0, 1, 3, 2, 6, 5, 4), graph.dfs(0))
        assertEquals(listOf(0, 1, 3, 2, 6), graph.dfs(0, 6))
        assertEquals(
            setOf(listOf(0, 2, 6), listOf(0, 2, 3, 5, 6), listOf(0, 1, 3, 2, 6), listOf(0, 1, 3, 5, 6)),
            graph.allPaths(0, 6)
        )
    }
}