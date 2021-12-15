import java.io.File

object Algorithms {

    fun searchFiles(parent: File, name: String, resultList: MutableList<File>) {
        if (parent.isDirectory) parent.listFiles()?.forEach {
            if (it.name.endsWith(name)) resultList.add(it)
            searchFiles(it, name, resultList)
        }
    }

    fun getParent(file: File, level: Int): File {
        var tmp = file
        for (i in 0..level) tmp = tmp.parentFile
        return tmp
    }

    fun groupByParent(files: List<File>, parentLevel: Int): Map<File, List<File>> {
        return files.map { getParent(it, parentLevel) to it }
            .groupBy { it.first }
            .mapValues { it.value.map { it.second } }
    }

    fun groupByParentName(files: List<File>, parentLevel: Int): Map<String, List<File>> {
        return files.map { getParent(it, parentLevel) to it }
            .groupBy { it.first.name }
            .mapValues { it.value.map { it.second } }
    }
}