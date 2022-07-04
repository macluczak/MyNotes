package com.macluczak.mywallet


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import java.lang.Math.abs

fun Path.calcFromTo(from: Offset, to: Offset ) {
    quadraticBezierTo(
    from.x,
    from.y,
        abs(from.x + to.x)/ 2f,
        abs(from.y + to.y)/ 2f
    )
}