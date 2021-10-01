import java.io.Console
import java.io.File

fun main(args: Array<String>) {
    println("args = ${args.toList()}")

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
    val map = Algorithms.groupByParent(results, parentLvl)
    map.forEach { t, u -> u.forEach { FileUtils.copy(it, File(copyTo, t.name)) } }
}