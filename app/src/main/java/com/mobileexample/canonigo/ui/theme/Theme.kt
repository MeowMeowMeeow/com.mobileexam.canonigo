package com.mobileexample.canonigo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NeonGreen = Color(0xFF7acc10)
val DarkBackground = Color(0xFF000000)
val WhiteText = Color(0xFFFFFFFF)
val DarkCyan = Color(0xFF0072b5)

val LightPrimary = NeonGreen
val LightBackground = Color(0xFFFFFFFF)
val LightOnPrimary = DarkBackground


val DarkPrimary = Color(0xFF144484)
val DarkBackgroundVariant = Color(0xFF000000)
val DarkOnPrimary = WhiteText
val DarkCardColor = Color(0xFF87D1DB)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkCyan ,
    background = DarkBackgroundVariant,
    onPrimary = DarkOnPrimary,
    surface = DarkCardColor,
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = NeonGreen,
    background = LightBackground,
    onPrimary = LightOnPrimary
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
