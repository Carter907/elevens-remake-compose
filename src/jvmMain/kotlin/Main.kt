import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.stream.IntStream


var cardService = CardFileService();


@Composable
@Preview
fun App() {

    MaterialTheme {
        Game("Elevens")
    }
}


@Composable
fun Game(title: String = "title") {
    var score = remember { mutableStateOf(0) }
    var lastIndex = remember {mutableStateOf(0)}
    val elevens = Elevens();
    val cards: List<String> = cardService.getAllCardNames().shuffled();
    var deck = remember {
        mutableStateListOf(
            *with(cards) {

                Array(9) {
                    lastIndex.value = it;
                    this[it]
                }
            }

        )

    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text("score ${score.value}")
                Button(
                    onClick = {
                        deck.shuffle();
                    },
                    content = { Text("randomize") }
                )

            }


        },
        modifier = Modifier.padding(20.dp)
    ) {

        PlayingDeck(cards = deck, elevens = elevens, score = score, remainingIndex = lastIndex, remainingCards = cards);


    }

}

@Composable
fun PlayingDeck(modifier: Modifier = Modifier,
                cards: SnapshotStateList<String>,
                elevens: Elevens, score: MutableState<Int>,
                remainingIndex: MutableState<Int>,
                remainingCards: List<String>
) {
    val clickedCards = remember {
        mutableStateListOf(*emptyArray<String>())
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (j in 0 until 3)
            Row(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0 until 3) {

                    val i_n = j * 3 + i
                    Card(name = cards[i_n],
                        scale = .5f,
                        modifier = Modifier

                            .clickable {
                                if (cards[i_n] !in clickedCards)
                                    clickedCards.add(cards[i_n])
                                if (clickedCards.size == 2) {
                                    if (elevens.addsToElevens(clickedCards[0], clickedCards[1])) {

                                        score.value = score.value + 1;
                                        cards.remove(clickedCards[0])
                                        cards.remove(clickedCards[1])

                                        cards.add(remainingCards[remainingIndex.value + 1])
                                        cards.add(remainingCards[remainingIndex.value + 2])
                                        remainingIndex.value += 2;


                                    }
                                    clickedCards.clear()

                                }
                            }
                    )
                }
            }
    }
}




@Composable
fun Card(name: String = cardService.getRanCardName(), modifier: Modifier = Modifier, scale: Float) {
    Image(
        modifier = modifier,
        painter = getCardSvg(name, scale = scale),
        contentDescription = null
    )

}


@Composable
fun getCardSvg(cardName: String, scale: Float): Painter {
    return loadSvgPainter(
        cardService.getSpecifiedCardStream("$cardName"),
        density = Density(scale)
    )
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Elevens"
    ) {
        App()
    }
}
