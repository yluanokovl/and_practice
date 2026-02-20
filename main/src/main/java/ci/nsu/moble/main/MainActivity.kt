package ci.nsu.moble.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import ci.nsu.moble.main.ui.theme.PracticeTheme

// Хранение цветов по названиям
object ColorsPalette {
    val colors: Map<String, Color> = mapOf(
        "Red" to Color(0xFFFF0000),
        "Green" to Color(0xFF00FF00),
        "Blue" to Color(0xFF0000FF),
        "Cyan" to Color(0xFF00FFFF),
        "Magenta" to Color(0xFFFF00FF),
        "Yellow" to Color(0xFFFFFF00),
        "Black" to Color(0xFF000000),
        "White" to Color(0xFFFFFFFF),
        "Orange" to Color(0xFFFFA500),
        "Purple" to Color(0xFF800080)
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                ScaffoldShortContent()
            }
        }
    }
}

@Composable
fun ScaffoldShortContent() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        GreetingWithPalette(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun GreetingWithPalette(modifier: Modifier = Modifier) {
    var inputColor by remember { mutableStateOf("") }
    var currentBackgroundColor by remember { mutableStateOf(Color.White) }
    var buttonColor by remember { mutableStateOf(Color.Gray) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Введите название цвета:")

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для ввода текста
        TextField(
            value = inputColor,
            onValueChange = { inputColor = it },
            placeholder = { Text(text = "Например, Red, Green") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка "Применить цвет" для изменения фона
        Button(
            onClick = {
                val containerColor = ColorsPalette.colors[inputColor.trim()]
                if (containerColor != null) {
                    buttonColor = containerColor
                } else {
                    Log.d("ColorSearch", "${inputColor} цвета нет :(")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .padding(top = 4.dp)
        ) {
            Text(text = "Применить цвет")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Область, которая меняет цвет фона
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(currentBackgroundColor)
                .padding(8.dp)
        ) {
            Column {
                Text(text = "Доступные палитры:")
                Spacer(modifier = Modifier.height(8.dp))
                PaletteList(
                    palette = ColorsPalette.colors.toList(),
                    onPick = { inputColor = it }
                )
            }
        }
    }
}

@Composable
fun PaletteList(
    palette: List<Pair<String, Color>>,
    onPick: (String) -> Unit
) {
    LazyColumn {
        items(palette) { (name, color) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onPick(name) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    BoxColor(color = color)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = name)
                }
            }
        }
    }
}

@Composable
fun BoxColor(color: Color) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .background(color = color)
    )
}