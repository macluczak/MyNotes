package com.macluczak.mywallet.bottom_menu

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val title: String,
    val route: String,
    @DrawableRes val iconId: Int
)
