import java.io.File

fun main(args: Array<String>) {
    println("args = ${args.toList()}")
    val searchIn = File(args[0])
    val name = args[1]
    val copyTo = args[2]
    val parentLvl = args[3].toInt()
    val results = mutableListOf<File>()

    Algorithms.searchFiles(searchIn, name, results)
    val map = Algorithms.groupByParent(results, parentLvl)
    map.forEach { t, u -> u.forEach { FileUtils.copy(it, File(copyTo, t.name)) } }
}