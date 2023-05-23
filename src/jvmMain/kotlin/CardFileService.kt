import java.io.File
import java.io.InputStream

class CardFileService {

    companion object {
        const val DIRECTORY_TO_FILES = "/SVG-cards-1.3";

    }


    fun getRanCardName(): String {



        return getAllCardNames().random()
    }

    fun getSpecifiedCardStream(name: String): InputStream = object {}::class.java.getResourceAsStream("$DIRECTORY_TO_FILES$name");

    fun getRanCardStream(): InputStream {

        return getSpecifiedCardStream(getRanCardName())
    }

    fun getAllCardNames(): MutableList<String> {

        val files = File(object {}::class.java.getResource(DIRECTORY_TO_FILES).file).listFiles();

        return files.toList().stream().map { it.name }.toList()
    }
}