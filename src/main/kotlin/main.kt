import java.io.Console
import java.io.File

fun main(args: Array<String>) {
    println("args = ${args.toList()}")
    println("gather - 1, broadcast - 2")
    when(System.console().readLine().toIntOrNull()) {
        1 -> modeGather()
        else -> Unit
    }
}

fun modeGather() {
    try {
        println("searchIn")
        val searchIn = File(System.console().readLine())
        println("name")
        val name = System.console().readLine()
        println("copyTo")
        val copyTo = System.console().readLine()
        println("parentLvl ")
        val parentLvl = System.console().readLine().toInt()

        val results = mutableListOf<File>()
        Algorithms.searchFiles(searchIn, name, results)
        println("results = $results")
        val map = Algorithms.groupByParent(results, parentLvl)
        println("map = $map")
        map.forEach { t, u -> u.forEach { FileUtils.copy(it, File(copyTo, t.name)) } }
        println("done")
    } catch (e: Exception) {
        println("error = $e")
    }
}