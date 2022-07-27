package com.macluczak.mywallet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.macluczak.mywallet.ui.theme.BlueNoteDark
import com.macluczak.mywallet.ui.theme.BlueNoteLight
import com.macluczak.mywallet.ui.theme.RedToDO
import com.macluczak.mywallet.ui.theme.YellowNote
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun TaskDetails(id: Int, viewModel: MainViewModel){
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.White)
    systemUiController.setNavigationBarColor(
        color = Color.White
    )

    val task = viewModel.getAllTasks().observeAsState().value?.singleOrNull{it.id == id}

    val taskState = remember {
        mutableStateOf(task!!.state)
    }

    Column() {
        Column(Modifier
            .fillMaxSize()
            .weight(1f)
            .background(BlueNoteLight)){
            Text(modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp), text = task?.title.toString(),
                style = MaterialTheme.typography.h4)
            chooseTaskState(state = taskState.value) {
                taskState.value = it
                val newTask = task!!.copy(state = taskState.value)
                viewModel.updateTask(newTask)
            }


        }
        Column(Modifier
            .fillMaxSize()
            .weight(3f)
            .background(YellowNote)) {
            Text(text = task?.description.toString())
        }

        Box(modifier = Modifier
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .fillMaxSize()
            .weight(1f)){

            Card(modifier = Modifier
                .padding(15.dp, 0.dp, 15.dp, 0.dp)

//                backgroundColor = Color.Blue

            ) {

                Column() {
                    Row(modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 0.dp)) {
                        Column() {
                            Text(text = task!!.startDate.dropLast(5),
                                style = MaterialTheme.typography.h6)
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        Column() {
                            Text(text = task!!.endDate.dropLast(5),
                                style = MaterialTheme.typography.h6)

                        }


                    }
                    Row(modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp)) {

                        Icon(painter = painterResource(id = R.drawable.ic_outline_circle_24),
                            "circle",
                            modifier =
                            Modifier.align(CenterVertically))
                        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(25f, 15f), 0f)
                        Canvas(Modifier
                            .weight(1f)
                            .align(CenterVertically)) {

                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                pathEffect = pathEffect,
                                strokeWidth = 7f
                            )
                        }
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_circle_24),
                            "circle",
                            modifier =
                            Modifier.align(CenterVertically))

                    }
                    if(!task!!.wholeDay) {
                        Row(modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceAround) {

                            Column() {
                                Text(text = task!!.startTime,
                                    style = MaterialTheme.typography.body1)

                            }
                            Spacer(modifier = Modifier.weight(1f))

                            Column() {

                                Text(text = task!!.endTime, style = MaterialTheme.typography.body1)

                            }


                        }
                    }
                    else{
                        Spacer(modifier = Modifier.size(15.dp))
                    }


                }


            }
        }





    }




}