import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")

    val results = mutableListOf<File>()
    Algorithms.searchFiles(File("src"), "main.kt", results)
    val map = Algorithms.groupByParent(results, 2)
    map.forEach { t, u ->
        u.forEach { FileUtils.copy(it, File("copies", t.name)) }
    }
}