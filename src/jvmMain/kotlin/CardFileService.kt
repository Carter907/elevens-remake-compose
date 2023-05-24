import java.io.File
import java.io.InputStream

class CardFileService {

    companion object {
        const val DIRECTORY_TO_FILES = "/SVG-cards-1.3/";

    }

    var cards: List<String> = emptyList();


    fun getRanCardName(): String {



        return getAllCardNames().random()
    }

    fun getSpecifiedCardStream(name: String): InputStream = object {}::class.java.getResourceAsStream("$DIRECTORY_TO_FILES$name");

    fun getRanCardStream(): InputStream {

        return getSpecifiedCardStream(getRanCardName())
    }

    fun getAllCardNames(): List<String> {

        if (cards.isEmpty()) {
           cards = File(object {}::class.java.getResource(DIRECTORY_TO_FILES).file).listFiles().toList().stream().map { it.name }.toList();
            println(cards);
            return cards;
        }

        println(cards);


        return cards;
    }
}