class Day4 {
    // 27027
    fun part1(): Int {
        val input = readLines(this)
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = getBoards(input)

        val resultPerBoard = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (number in numbers) {
            for (boardNr in boards.indices) {
                findMatch(boards[boardNr], number)?.let {
                    val result = resultPerBoard.getOrDefault(boardNr, mutableListOf())
                    result.add(it)
                    resultPerBoard[boardNr] = result
                    if (win(result)) {
                        return calculateScore(boards[boardNr], result, number)
                    }
                }
            }
        }
        return -1
    }

    // 36975
    fun part2(): Int {
        val input = readLines(this)
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = getBoards(input)

        val resultPerBoard = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        var winNumber: Int? = null
        val winBoardsNr = mutableListOf<Int>()
        var winBoardNr: Int? = null
        for (number in numbers) {
            for (boardNr in boards.indices.filterIndexed { _, i -> !winBoardsNr.contains(i) }) {
                findMatch(boards[boardNr], number)?.let {
                    val result = resultPerBoard.getOrDefault(boardNr, mutableListOf())
                    result.add(it)
                    resultPerBoard[boardNr] = result
                    if (win(result)) {
                        winBoardNr = boardNr
                        winNumber = number
                        winBoardsNr.add(boardNr)
                    }
                }
            }

            if (winBoardsNr.size == boards.size) break
        }

        return calculateScore(boards[winBoardNr!!], resultPerBoard[winBoardNr]!!, winNumber!!)
    }

    private fun calculateScore(
        board: List<List<Int>>,
        matchedCells: List<Pair<Int, Int>>,
        winNumber: Int
    ): Int {
        var sum = 0
        board.indices.forEach { x ->
            board[x].indices.forEach { y ->
                if (!matchedCells.contains(Pair(x, y))) sum += board[x][y]
            }
        }
        return sum * winNumber
    }

    private fun getBoards(input: List<String>): List<List<List<Int>>> {
        val boards = mutableListOf<List<List<Int>>>()
        var board = mutableListOf<List<Int>>()
        input.takeLast(input.size - 2).forEach { row ->
            if (row.isBlank()) {
                boards.add(board)
                board = mutableListOf()
            } else
                board.add(row.split(" ").filterNot { it.isBlank() }.map { it.toInt() })
        }
        // Add last board
        boards.add(board)
        return boards.toList()
    }

    private fun findMatch(board: List<List<Int>>, num: Int): Pair<Int, Int>? {
        board.indices.forEach { row ->
            board[row].indices.forEach { column ->
                if (board[row][column] == num) return Pair(
                    row,
                    column
                )
            }
        }
        return null
    }

    private fun win(results: List<Pair<Int, Int>>): Boolean {
        val rows = results.map { it.first }.groupingBy { it }.eachCount()
        val columns = results.map { it.second }.groupingBy { it }.eachCount()
        if (rows.maxOfOrNull { it.value } == 5 || columns.maxOfOrNull { it.value } == 5) {
            return true
        }
        return false
    }
}