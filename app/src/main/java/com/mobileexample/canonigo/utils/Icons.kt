package com.mobileexample.canonigo.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.mobileexample.canonigo.R


fun statusIcon(status: String): Int{
    return when (status.lowercase()) {
        "alive"-> R.drawable.alive
        "dead" -> R.drawable.dead
        else -> R.drawable.unknown_
    }
}


fun genderIcon(gender: String): Int {
    return when (gender.lowercase()) {
        "male" -> R.drawable.male
        "female" -> R.drawable.female
        else -> R.drawable.agender
    }
}


fun speciesIcon(species: String): Int {
    return when (species.lowercase()) {
        "human"-> R.drawable.human
        "alien" -> R.drawable.alien
        else -> R.drawable.human
    }
}


fun typeIcon(type: String): Int {
    return when {
        type.contains("parasite", ignoreCase = true) -> R.drawable.parasite
        type.contains("antenna", ignoreCase = true) -> R.drawable.antenna
        type.contains("test tube", ignoreCase = true) -> R.drawable.testtube
        type.contains("train", ignoreCase = true) -> R.drawable.train
        type.contains("ant", ignoreCase = true) -> R.drawable.ant
        else -> R.drawable.uknown
    }
}


    fun statusColor(status: String): Color {

        return when (status.lowercase()) {
            "alive" -> Color.Green
            "dead" -> Color.Red
            else -> Color(0xFFFFA500)
        }
    }

@Composable
fun statusDetailsColor(status: String): Color {
    val isDarkMode = isSystemInDarkTheme()

    return when (status.lowercase()) {
        "alive" -> if (isDarkMode) MaterialTheme.colorScheme.tertiary else Color.Green
        "dead" -> if (isDarkMode) Color.Black else Color.Red
        else -> if (isDarkMode) Color.DarkGray else Color(0xFFFFA500)
    }
}



