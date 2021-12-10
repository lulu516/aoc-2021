import java.util.Stack

class Day10 {
    data class ChunkPair(val start: Char, val end: Char)
    data class ParseResult(val wrongChar: Char? = null, val missingChars: List<Char>? = null)

    private val startToChunkPair: Map<Char, ChunkPair> = mapOf(
        '(' to ChunkPair('(', ')'),
        '[' to ChunkPair('[', ']'),
        '{' to ChunkPair('{', '}'),
        '<' to ChunkPair('<', '>')
    )
    private val corruptionScore: Map<Char, Int> = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val completionScore: Map<Char, Int> = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    // 374061
    fun part1(): Int {
        val corruptedLines = readLines(this).mapNotNull { parse(it).wrongChar }
        return corruptedLines.sumOf { corruptionScore[it]!! }
    }

    // 2116639949
    fun part2(): Long {
        val completionScores =
            readLines(this).mapNotNull { parse(it).missingChars }.map { completionScore(it) }.sorted()
        return completionScores[completionScores.size / 2]
    }

    private fun completionScore(chars: List<Char>): Long {
        return chars.map { completionScore[it]!!.toLong() }.reduce { acc, i -> acc * 5 + i }
    }

    private fun parse(str: String): ParseResult {
        val stack: Stack<ChunkPair> = Stack<ChunkPair>()
        for (c: Char in str.toCharArray()) {
            if (startToChunkPair.containsKey(c)) {
                stack.push(startToChunkPair[c])
            } else {
                if (c != stack.pop().end) {
                    return ParseResult(c)
                }
            }
        }
        return ParseResult(missingChars = stack.reversed().map { it.end })
    }
}