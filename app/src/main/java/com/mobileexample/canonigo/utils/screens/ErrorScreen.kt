package com.mobileexample.canonigo.utils.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.mobileexample.canonigo.R
import kotlinx.coroutines.delay

@Composable
fun ErrorScreen(onAnimationFinished: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2000)
        onAnimationFinished()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {

            Image(
                painter = painterResource(id = R.drawable.error),
                contentDescription = "error Screen Image",
                        modifier = Modifier.fillMaxSize(),

            )

    }
}
