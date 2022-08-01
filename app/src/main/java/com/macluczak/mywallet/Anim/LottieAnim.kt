package com.macluczak.mywallet

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.macluczak.mywallet.ui.theme.HippieBlue300

@Composable
fun animEmpty(){
    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(

        LottieCompositionSpec
            .RawRes(R.raw.emptlottie)
    )

    val progress by animateLottieCompositionAsState(

        composition,

        iterations = LottieConstants.IterateForever,


        isPlaying = isPlaying,

        speed = speed,

        restartOnPlay = false

    )
    Box(Modifier.fillMaxSize()){



            Box(Modifier
                .padding(20.dp, 30.dp, 20.dp, 0.dp).fillMaxWidth().align(Alignment.TopCenter
                )) {
                Text("a little empty here!", modifier = Modifier
                    .align(Alignment.Center),
                    style = MaterialTheme.typography.h5, color = HippieBlue300)
            }
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(400.dp)

            )





    }


}

@Composable
fun animStart(){
    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(

        LottieCompositionSpec

            .RawRes(R.raw.start)
    )


    val progress by animateLottieCompositionAsState(

        composition,

        iterations = LottieConstants.IterateForever,

        isPlaying = isPlaying,

        speed = speed,


        restartOnPlay = false

    )
    Box(Modifier.fillMaxSize()){

        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {

        Box(Modifier
            .padding(20.dp, 20.dp, 20.dp, 0.dp).fillMaxWidth()) {
            Text("Let's create your reminders!", modifier = Modifier
                .align(Alignment.Center),
                style = MaterialTheme.typography.h5, color = HippieBlue300)
        }
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .size(400.dp)

            )

        }



    }


}

@Composable
fun animAstro(){
    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(

        LottieCompositionSpec

            .RawRes(R.raw.astronaut)
    )


    val progress by animateLottieCompositionAsState(

        composition,

        iterations = LottieConstants.IterateForever,

        isPlaying = isPlaying,

        speed = speed,


        restartOnPlay = false

    )
    Box(Modifier.fillMaxSize()){


            Box(Modifier
                .padding(20.dp, 5.dp, 60.dp, 0.dp).fillMaxWidth().align(Alignment.TopStart)) {
                Text("Nothing here...", modifier = Modifier
                    .align(Alignment.TopEnd),
                    style = MaterialTheme.typography.h6, color = HippieBlue300)
            }
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(400.dp, 160.dp)

            )





    }


}