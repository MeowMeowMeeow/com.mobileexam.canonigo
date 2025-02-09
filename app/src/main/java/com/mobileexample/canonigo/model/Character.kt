package com.mobileexample.canonigo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Character (

    @StringRes val name: Int,
    @StringRes val status: Int,
    @StringRes val species: Int,
    @StringRes val type: Int,
    @StringRes val gender: Int,
    @StringRes val nameO: Int,
    @StringRes val nameL: Int,
    @DrawableRes val image: Int,
    )