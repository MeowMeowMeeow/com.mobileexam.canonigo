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
import androidx.navigation.compose.rememberNavController
import com.mobileexample.canonigo.data.NetworkRepository
import com.mobileexample.canonigo.data.Repository
import com.mobileexample.canonigo.data.Wubalubadubdub
import com.mobileexample.canonigo.network.ApiService
import com.mobileexample.canonigo.ui.theme.RickAndMortyTheme
import com.mobileexample.canonigo.utils.screens.RickandMortyApp
import com.mobileexample.canonigo.utils.screens.RickandMortyList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                RickandMortyApp(navController = navController)
            }
        }
    }
}