package com.mobileexample.canonigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobileexample.canonigo.data.Wubalubadubdub
import com.mobileexample.canonigo.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var showStarterScreen by remember { mutableStateOf(true) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showStarterScreen) {
            StarterScreen(
                onAnimationFinished = { showStarterScreen = false }
            )
        } else {
            RickandMortyApp(
                characters = Wubalubadubdub().loadCharacters(),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    RickAndMortyTheme {
        MainScreen()  // Preview the MainScreen composable
    }
}
