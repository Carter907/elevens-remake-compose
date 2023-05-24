import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
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
        TitleScreen("Elevens")
    }
}


@Composable
fun TitleScreen(title: String = "title") {
    var score by remember { mutableStateOf(0) }
    val cards = cardService.getAllCardNames();

    var deck = remember {
        mutableStateListOf(
            *Array(9) {
                cards.random()
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
                Text("score $score")
                Button(
                    onClick = {

                    },
                    content = { Text("randomize") }
                )

            }


        },
        modifier = Modifier.padding(20.dp)
    ) {

        PlayingDeck(deck);


    }

}

@Composable
fun PlayingDeck(cards: SnapshotStateList<String>) {

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

                    val i_n =
                    println(i_n)
                    Card(name = cards[i_n], scale = .5f, modifier = Modifier.fillMaxSize())
                }
            }
    }
}

@Composable
fun Card(name: String = cardService.getRanCardName(), modifier: Modifier = Modifier, scale: Float) {
    Image(
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
