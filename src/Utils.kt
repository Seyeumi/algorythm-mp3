import java.io.File

fun File.toTypedArray() = this.readLines()
        . flatMap { it.split (" ") }
        . filter { it.isNotEmpty () }
        .map {
            it.toLowerCase()
                    .replace(Regex("[^A-Za-z0-9]"), "") }
        .toTypedArray()