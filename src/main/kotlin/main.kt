import java.io.Console
import java.io.File

fun main(args: Array<String>) {
    println("args = ${args.toList()}")
    println(AppMode.allToString())
    when (AppMode.find(readLine()?.toIntOrNull())) {
        AppMode.Gather -> modeGather()
        AppMode.BroadcastStringsLocalizations -> modeBroadcastStringsLocalizations()
        AppMode.FixStringXmlApostrophe -> encapsulateApostrophe()
        else -> Unit
    }
}

enum class AppMode(val code: Int) {
    Gather(1),
    BroadcastStringsLocalizations(2),
    FixStringXmlApostrophe(3);

    companion object {
        fun allToString() = values().map { "${it.name} - ${it.code}" }.reduce { acc, s -> acc + ", " + s }

        fun find(code: Int?) = values().find { it.code == code }
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
                val newDir = "${copyTo.absolutePath}/${it.key}/src/main/res/values$localizationKey/"
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

fun encapsulateApostrophe() {
    try {
        println("searchIn")
        val searchIn = File(readLine())
        val name = "strings.xml"
        println("name = $name")

        val results = mutableListOf<File>()
        Algorithms.searchFiles(searchIn, name, results)
        println("results = $results")
        results.forEach { StringFileUtils.encapsulateApostrophe(it) }
        println("done")
    } catch (e: Exception) {
        println("error = $e")
    }
    val tmp = "deEawD6kNi6p6Wf"
}
