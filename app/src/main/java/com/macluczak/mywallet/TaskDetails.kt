package com.macluczak.mywallet

import android.graphics.Paint
import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun TaskDetails(id: Int, viewModel: MainViewModel, navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = HippieBlue100)
    systemUiController.setNavigationBarColor(
        color = HippieBlue100
    )

    val task = viewModel.getAllTasks().observeAsState().value?.singleOrNull { it.id == id }

    val taskState = remember {
        mutableStateOf(task!!.state)
    }

    BoxWithConstraints(modifier = Modifier
        .background(HippieBlue50)
        .fillMaxSize()
        .blur(20.dp))
    {

        val height = constraints.maxHeight
        val width = constraints.maxWidth

        CanvasBackground(height = height, width = width)
    }

    Box(Modifier
        .fillMaxSize()
    ) {

        Surface(shape = CircleShape, color = Coral,
            elevation = 7.dp, modifier =
            Modifier
                .fillMaxWidth()
                .padding(20.dp, 15.dp, 20.dp, 20.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    navController.popBackStack()
                    viewModel.deleteTask(task!!)
                }

        ) {
            Box(Modifier.align(Alignment.Center)) {
                Text(text = "Complete! ",
                    color = Color.White,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .align(Alignment.Center))

            }
        }
        Column() {
            Surface(modifier = Modifier,
                elevation = 7.dp, color = HippieBlue100) {

                Column(Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp, 0.dp, 10.dp)) {

                    Row(Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp), Arrangement.SpaceBetween) {
                        Text(text = task!!.title, style = MaterialTheme.typography.h6,
                            color = BlackCurrant,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(20.dp, 0.dp, 15.dp, 0.dp))
//                        Text(text = Task.taskState[type.value],
//                            style = MaterialTheme.typography.body1,
//                            color = BlackCurrant,
//                            modifier = Modifier
//                                .padding(20.dp, 0.dp, 20.dp, 0.dp))
                    }
                    chooseTaskState(state = taskState.value) {
                        taskState.value = it
                        val newtask = task!!.copy(state = it)
                        viewModel.updateTask(newtask)


                    }

                }

            }


            Row(Modifier
                .fillMaxSize()
                .padding(0.dp, 30.dp, 0.dp, 80.dp)) {
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(25f, 15f), 0f)

                Column(Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(0.dp, 27.dp, 0.dp, 40.dp), verticalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.ic_outline_circle_24),
                        "circle",
                        modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(24.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp),
                        tint = HippieBlue400)

                    Canvas(Modifier
                        .weight(2f)
                        .align(Alignment.CenterHorizontally)) {

                        drawLine(
                            color = HippieBlue400,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2f
                        )

                    }

                    Icon(painter = painterResource(id = R.drawable.ic_outline_circle_24),
                        "circle",
                        modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(24.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp),
                        tint = HippieBlue400)

                    Canvas(Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally)) {

                        drawLine(
                            color = HippieBlue400,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2f
                        )

                    }
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_circle_24),
                        "circle",
                        modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(24.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp),
                        tint = HippieBlue400)
                }

                Column(Modifier
                    .fillMaxSize()
                    .weight(6f)
                    .padding(0.dp, 30.dp, 30.dp, 0.dp)) {

                    Box(Modifier
                        .fillMaxSize()
                        .weight(5.8f))
                    {
                        Surface(shape = RoundedCornerShape(5.dp, 5.dp, 50.dp, 5.dp),
                            elevation = 10.dp,
                            color = if (Build.VERSION.SDK_INT >= 32) HippieBlue50 else HippieBlue,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.20f)) {

                            if (Build.VERSION.SDK_INT >= 32) {
                                BoxWithConstraints(modifier = Modifier
                                    .fillMaxSize()
                                    .blur(50.dp)) {
                                    val height = constraints.maxHeight
                                    val width = constraints.maxWidth
                                    CanvasGlassTask(height = height, width = width)

                                }

                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .background( HippieBlueA))

                            }



                            LazyColumn() {
                                item {
                                    Row(Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp, 5.dp, 15.dp, 15.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = "Start: ${task!!.startDate}",
                                            color = Color.White,
                                            style = MaterialTheme.typography.h6)

                                        if (!task.wholeDay) {
                                            Text(text = task!!.startTime,
                                                color = Color.White,
                                                fontWeight = FontWeight.Thin,
                                                fontStyle = FontStyle.Italic,
                                                style = MaterialTheme.typography.h6)
                                        }

                                    }
                                }
                                item {
                                    Row(Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 5.dp, 5.dp, 15.dp)) {
                                        Text(text = task!!.description,
                                            color = Color.White,
                                            style = MaterialTheme.typography.body1)
                                    }
                                }

                            }


                        }
                    }
                    Box(Modifier
                        .fillMaxSize()
                        .weight(3.2f))
                    {
                        Text(
                            text = "Current, ${Task.taskState[taskState.value]}",
                            style = MaterialTheme.typography.h6,
                            color = HippieBlue,
                            modifier = Modifier.align(TopStart)
                        )
                    }
                    Box(Modifier
                        .fillMaxSize()
                        .weight(1.3f)) {

                        Row(Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 20.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {

                            Text(
                                text = "Deadline: ${task!!.endDate}",
                                style = MaterialTheme.typography.h6,
                                color = HippieBlue,

                                )

                            if (!task.wholeDay) {
                                Text(
                                    text = task.endTime,
                                    style = MaterialTheme.typography.h6,
                                    color = HippieBlue,
                                    fontWeight = FontWeight.Thin,
                                    fontStyle = FontStyle.Italic
                                )
                            }

                        }

                    }

                }

            }

        }
//            Column(Modifier
//                .fillMaxSize()
//                .weight(3f)
//                ) {
//                Text(text = task?.description.toString())
//            }

//            Box(modifier = Modifier
//                .padding(15.dp, 0.dp, 15.dp, 0.dp)
//                .fillMaxSize()
//                .weight(1f)) {
//


//                Card(modifier = Modifier
//                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
//
////                backgroundColor = Color.Blue
//
//                ) {
//
//                    Column() {
//                        Row(modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 0.dp)) {
//                            Column() {
//                                Text(text = task!!.startDate.dropLast(5),
//                                    style = MaterialTheme.typography.h6)
//                            }
//                            Spacer(modifier = Modifier.weight(1f))
//
//                            Column() {
//                                Text(text = task!!.endDate.dropLast(5),
//                                    style = MaterialTheme.typography.h6)
//
//                            }
//
//
//                        }
//                        Row(modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp)) {
//
//                            Icon(painter = painterResource(id = R.drawable.ic_outline_circle_24),
//                                "circle",
//                                modifier =
//                                Modifier.align(CenterVertically))
//                            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(25f, 15f), 0f)
//                            Canvas(Modifier
//                                .weight(1f)
//                                .align(CenterVertically)) {
//
//                                drawLine(
//                                    color = Color.Black,
//                                    start = Offset(0f, 0f),
//                                    end = Offset(size.width, 0f),
//                                    pathEffect = pathEffect,
//                                    strokeWidth = 7f
//                                )
//                            }
//                            Icon(painter = painterResource(id = R.drawable.ic_baseline_circle_24),
//                                "circle",
//                                modifier =
//                                Modifier.align(CenterVertically))
//
//                        }
//                        if (!task!!.wholeDay) {
//                            Row(modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp),
//                                horizontalArrangement = Arrangement.SpaceAround) {
//
//                                Column() {
//                                    Text(text = task!!.startTime,
//                                        style = MaterialTheme.typography.body1)
//
//                                }
//                                Spacer(modifier = Modifier.weight(1f))
//
//                                Column() {
//
//                                    Text(text = task!!.endTime,
//                                        style = MaterialTheme.typography.body1)
//
//                                }
//
//
//                            }
//                        } else {
//                            Spacer(modifier = Modifier.size(15.dp))
//                        }
//
//
//                    }
//
//
//                }
//            }


    }
}



