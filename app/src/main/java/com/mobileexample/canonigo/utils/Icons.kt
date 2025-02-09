package com.mobileexample.canonigo.utils

import androidx.annotation.DrawableRes
import com.mobileexample.canonigo.R

@DrawableRes
fun statusIcon(status: Int): Int {
    return when (status) {
        R.string.Alive -> R.drawable.alive
        R.string.Dead -> R.drawable.dead
        else -> R.drawable.unknown_
    }
}

@DrawableRes
fun genderIcon(gender: Int): Int {
    return when (gender) {
        R.string.Male -> R.drawable.male
        R.string.Female -> R.drawable.female
        else -> R.drawable.agender
    }
}

@DrawableRes
fun speciesIcon(species: Int): Int {
    return when (species) {
        R.string.Human -> R.drawable.human
        R.string.Alien -> R.drawable.alien
        else -> R.drawable.human
    }
}

@DrawableRes
fun typeIcon(type: Int): Int {
    return when (type) {
        R.string.type16 -> R.drawable.parasite
        R.string.type18-> R.drawable.antenna
        R.string.type7 -> R.drawable.testtube
        R.string.type10 -> R.drawable.train
        R.string.type20 -> R.drawable.ant
        else -> R.drawable.uknown
    }
}