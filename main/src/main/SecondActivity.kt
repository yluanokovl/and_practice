package ci.nsu.moble.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = intent.getStringExtra("user_text") ?: "Нет данных"

        setContent {
            PracticeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(text = "Получен текст: $message")
                    }
                }
            }
        }
    }
}