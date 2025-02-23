package com.mobileexample.canonigo.utils.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mobileexample.canonigo.R
import kotlinx.coroutines.delay

@Composable
fun StarterScreen(onAnimationFinished: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2000)
        onAnimationFinished()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.pixelr),
                contentDescription = "Starting Screen Image",
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}
