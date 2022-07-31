package com.macluczak.mywallet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun TaskItem(item: Task, viewModel: MainViewModel, navController: NavController) {
    val stateText = when(item.state){
        0 -> "To-Do"
        1 -> "In Progress"
        else -> "Done"
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp, 20.dp, 5.dp)
            .height(130.dp)

            .clip(RoundedCornerShape(25.dp))
            .background(HippieBlue200)
            .clickable {
                navController.navigate(Screen.TaskDetail.withArgs(item.id))
            }


    ) {
        Box(modifier = Modifier
            .padding(1.dp, 1.dp, 1.dp, 1.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(25.dp))
            .align(Alignment.Center)
            .background(HippieBlue50))
        {
            Box(modifier = Modifier .padding(15.dp, 10.dp, 5.dp, 5.dp)) {
                Box(modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(25.dp))
                    .background(if(item.state == 0 ) BlueNoteLight
                    else if(item.state == 1) CoralLight
                    else GreenNote)
                    .padding(10.dp, 5.dp, 10.dp, 5.dp)
                ) {
                    Text(text = stateText, color = if(item.state == 0 ) BlueNote
                    else if(item.state == 1) Coral
                    else GreenDone , style = MaterialTheme.typography.body1)
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                Text(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterVertically),
                    text = item.title,
                    style = MaterialTheme.typography.h6,
                    color = HippieBlue400,
                    maxLines = 1,
                    softWrap = true


                )
            }

            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
//                Row(modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 0.dp)) {
//                    Column() {
//                        Text(text = item!!.startDate.dropLast(5),
//                            style = MaterialTheme.typography.h6)
//                    }
//                    Spacer(modifier = Modifier.weight(1f))
//
//                    Column() {
//                        Text(text = item!!.endDate.dropLast(5),
//                            style = MaterialTheme.typography.h6)
//
//                    }
//
//
//                }
                Row(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 15.dp)) {
                    Text(text = item!!.startDate.dropLast(5), modifier = Modifier.padding(0.dp, 0.dp, 3.dp, 0.dp),
                        style = MaterialTheme.typography.body1, color = HippieBlue400)
//                    Text( text ="-",
//                        style = MaterialTheme.typography.body1, color = HippieBlue400)
//                    Icon(painter = painterResource(id = R.drawable.ic_outline_circle_24),
//                        "circle",
//                        modifier =
//                        Modifier.align(Alignment.CenterVertically),
//                    tint = HippieBlue400)
                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(25f, 15f), 0f)
                    Canvas(Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)) {

                        drawLine(
                            color = HippieBlue400,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 2f
                        )

                    }
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_circle_24),
                        "circle",
                        modifier =
                        Modifier.align(Alignment.CenterVertically).size(15.dp)
                            .padding(0.dp, 0.dp, 8.dp, 0.dp),
                        tint = HippieBlue400)
                    Text(text = item!!.endDate.dropLast(5),
                        style = MaterialTheme.typography.body1, color = HippieBlue400)




                }


            }

        }

    }

}

@Composable
fun DisplayTasks(tasks: List<Task>, viewModel: MainViewModel, navController: NavController) {

    var searchByWord = viewModel.searchWord.observeAsState()

    var tasksFiltered = tasks.filter {
        it.title.contains(searchByWord.value ?: "")
    }

    LazyColumn(modifier = Modifier
        .padding(20.dp, 0.dp, 20.dp, 0.dp)) {

        items(tasksFiltered.size) { index ->
            if (tasksFiltered.isNotEmpty()) {
                TaskItem(item = tasksFiltered[index],
                    viewModel = viewModel,
                    navController = navController)
            }

        }
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
            ) {

            }

        }
    }
}