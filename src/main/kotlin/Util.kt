fun readLines(c: Any): List<String> = c.javaClass.getResourceAsStream(c.javaClass.name)!!.bufferedReader().readLines()


fun readLine(c: Any, separator: String): List<String> =
    c.javaClass.getResourceAsStream(c.javaClass.name)!!.bufferedReader().readLine().split(separator)

fun String.fromBinary() = Integer.parseInt(this, 2)

fun Int.sigma(): Int = if (this == 0) 0 else (1..this).sum()