import java.util.regex.Pattern

class Elevens {

    val values: HashMap<String, Int> = hashMapOf(
        "joker" to 0,
        "ace" to 1,
        "jack" to 11,
        "queen" to 12,
        "king" to 13,
    );

    fun getCardValue(card: String): Int {
        values.forEach {
            val pat = Pattern.compile(it.key)
            val matc = pat.matcher(card)
            if (matc.find())
                return it.value;

        }
        if (card.substring(0,2).all { it.isDigit() })
            return card.substring(0,2).toInt();
        if (card[0].isDigit())
            return card[0].digitToInt()

        return 0;
    }

    fun addsToElevens(card1: String, card2: String, card3: String = ""): Boolean {

        return getCardValue(card1) + getCardValue(card2) == 11;

    }

}