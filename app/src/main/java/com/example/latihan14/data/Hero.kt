package com.example.latihan14.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
   @StringRes val descriptionRes: Int
)
