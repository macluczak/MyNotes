package com.macluczak.mywallet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = BlueNote,
    primaryVariant = BlueNote,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = BlueNote,
    primaryVariant = BlueNote,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyWalletTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    if(darkTheme){

        systemUiController.setStatusBarColor(
            color = BlueNote

        )
        systemUiController.setNavigationBarColor(
            color = BlueNoteDark
        )
    }else{
        systemUiController.setStatusBarColor(
            color = BlueNote
        )
        systemUiController.setNavigationBarColor(
            color = BlueNoteDark
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}