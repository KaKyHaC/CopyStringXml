import java.io.File

object FileUtils {

    fun copy(file: File, targetDir: File, namePrefix: String? = null) {
        val newFile = File(targetDir.absolutePath, namePrefix.orEmpty() + file.name)
        newFile.createNewFile()
        file.copyTo(newFile, true)
    }
}