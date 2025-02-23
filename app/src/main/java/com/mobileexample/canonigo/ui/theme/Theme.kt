package com.mobileexample.canonigo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkCyan ,
    background = DarkBackgroundVariant,
    onPrimary = DarkOnPrimary,
    surface = DarkCardColor,
    onSurface = bars,
    tertiary = DarkCyan,
    onTertiary = DarkCyan

)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = NeonGreen,
    background = LightBackground,
    onPrimary = LightOnPrimary,
    onSurface = NeonGreen,
    tertiary = LightPrimary,
    onTertiary = WhiteText,

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
