import java.io.File

object Algorithms {

    fun searchFiles(parent: File, name: String, resultList: MutableList<File>) {
        if (parent.isDirectory) parent.listFiles()?.forEach {
            if (it.name.endsWith(name)) resultList.add(it)
            searchFiles(it, name, resultList)
        }
    }
}