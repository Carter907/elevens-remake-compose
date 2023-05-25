class Elevens {

    fun addsToElevens(card1: String, card2: String, card3: String = ""): Boolean {
        var num1 = 0;

        if (card1[0].isDigit() && card2[0].isDigit())
            return card1[0].digitToInt() + card2[0].digitToInt() == 11;
        if (card1.isAce() && card2[0].isDigit())
            return 1 + card2[0].digitToInt() == 11;
        return false;
    }

    private fun String.isAce(): Boolean {

        return this.length == 3 && this.substring(0,4) == "ace";

    }
}