package com.macluczak.mywallet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.macluczak.mywallet.ui.theme.HippieBlue100
import com.macluczak.mywallet.ui.theme.HippieBlue200
import com.macluczak.mywallet.ui.theme.HippieBlue50

@Composable
fun CanvasBackground(height: Int, width: Int){


    val mediumTonePoint1 = Offset(0f, height * 0.20f)
    val mediumTonePoint2 = Offset(width * 0.1f, height * 0.23f)
    val mediumTonePoint3 = Offset(width * 0.4f, height * 0.21f)
    val mediumTonePoint4 = Offset(width * 0.75f, height * 0.7f)
    val mediumTonePoint5 = Offset(width * 1.5f, -height.toFloat())

    val mediumTonePath = Path().apply {

        moveTo(mediumTonePoint1.x, mediumTonePoint1.y)

        calcFromTo(mediumTonePoint1, mediumTonePoint2)
        calcFromTo(mediumTonePoint2, mediumTonePoint3)
        calcFromTo(mediumTonePoint3, mediumTonePoint4)
        calcFromTo(mediumTonePoint4, mediumTonePoint5)

        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()

    }

    val lightTonePoint1 = Offset(0f, height * 0.35f)
    val lightTonePoint2 = Offset(width * 0.1f, height * 0.55f)
    val lightTonePoint3 = Offset(width * 0.3f, height * 0.38f)
    val lightTonePoint4 = Offset(width * 0.65f, height* 0.8f)
    val lightTonePoint5 = Offset(width * 1.4f, height* 0.9f)

    val lightTonePath = Path().apply {

        moveTo(lightTonePoint1.x, lightTonePoint1.y)

        calcFromTo(lightTonePoint1, lightTonePoint2)
        calcFromTo(lightTonePoint2, lightTonePoint3)
        calcFromTo(lightTonePoint3, lightTonePoint4)
        calcFromTo(lightTonePoint4, lightTonePoint5)

        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()

    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        ){

        drawPath(path = mediumTonePath,
            color = HippieBlue200)

        drawPath(path = lightTonePath,
            color = HippieBlue50)


    }
}