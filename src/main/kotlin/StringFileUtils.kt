import java.io.File

object StringFileUtils {

    fun encapsulateApostrophe(file: File) {
        file.writeText(encapsulateApostrophe(file.readText()))
    }

    fun encapsulateApostrophe(text: String): String {
        val buffer = StringBuffer(text)
        buffer.forEachIndexed { index, c ->
            if (c == '\'' && buffer[index - 1] != '\\') {
                println("$c at $index")
                buffer.insert(index, '\\')
            }
        }
        return buffer.toString()
    }
}