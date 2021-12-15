import java.io.Console
import java.io.File

fun main(args: Array<String>) {
    println("args = ${args.toList()}")
    println("gather - 1,  broadcastStringsLocalizations - 2")
    when (readLine()?.toIntOrNull()) {
        1 -> modeGather()
        2 -> modeBroadcastStringsLocalizations()
        else -> Unit
    }
}

fun modeBroadcastStringsLocalizations() {
    try {
        println("copyFrom")
        val originFile = File(readLine())
        println("copyTo")
        val copyTo = File(readLine())
        println("start")

        val stringFiles = mutableListOf<File>()
        Algorithms.searchFiles(originFile, ".xml", stringFiles)
        val mapModuleStrings = Algorithms.groupByParentName(stringFiles, 0)
        mapModuleStrings.forEach {
            it.value.forEach { stringFile ->
                val localizationKey = Utils.stringLocalizationKey(stringFile.name)
                val newDir = "${copyTo.absolutePath}/${it.key}/src/res/values$localizationKey/"
                FileUtils.copy(stringFile, File(newDir), null, "strings.xml")
            }
        }

        println("done")
    } catch (e: Exception) {
        println("error = $e")
    }
}

fun modeGather() {
    try {
        println("searchIn")
        val searchIn = File(readLine())
        println("name")
        val name = readLine().orEmpty()
        println("copyTo")
        val copyTo = readLine()
        println("parentLvl ")
        val parentLvl = readLine().orEmpty().toInt()

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