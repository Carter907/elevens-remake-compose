import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.net.URL

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        TitleScreen("Elevens")
    }
}


@Composable
fun TitleScreen(title: String = "title") {


    Scaffold(
        topBar = { Text(title) },
        modifier = Modifier.padding(20.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Card(scale = .5f, modifier = Modifier.fillMaxSize())
            Card(scale = .5f, modifier = Modifier.fillMaxSize())


        }
    }

}

@Composable
fun Card(cardName: String = "ace_of_clubs", modifier: Modifier = Modifier, scale: Float) {
    Image(
        painter = getCardSvg(cardName, scale = scale),
        contentDescription = null
    )

}


@Composable
fun getCardSvg(cardName: String, scale: Float): Painter =

    loadSvgPainter(
        object{}::class.java.getResourceAsStream("/SVG-cards-1.3/$cardName.svg"),
        density = Density(scale)
    )





fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
