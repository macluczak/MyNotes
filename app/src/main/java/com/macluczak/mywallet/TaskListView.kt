package com.macluczak.mywallet

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp, 20.dp, 5.dp)
            .height(140.dp)

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
                    .background(CoralLight)
                    .padding(10.dp, 5.dp, 10.dp, 5.dp)
                ) {
                    Text(text = "In Progress", color = Coral, style = MaterialTheme.typography.body1)
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                Text(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
                    text = item.title,
                    style = MaterialTheme.typography.h6,
                    color = HippieBlue400

                )


                if (viewModel.markAsDone.value == true) {
                    Box(modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxHeight()
                        .background(GreenDone)

                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_check_24),
                            contentDescription = "Done",
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.Center),
                            tint = Color.White)
                    }

                }


            }
        }//        Text(modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .align(Alignment.CenterEnd),
//            text = item.id.toString(),
//            fontSize = 16.sp)
    }

}

@Composable
fun DisplayTasks(tasks: List<Task>, viewModel: MainViewModel, navController: NavController) {

    var searchByWord = viewModel.searchWord.observeAsState()

    var tasksFiltered = tasks.filter {
        it.title.contains(searchByWord.value ?: "")
    }

    LazyColumn(modifier = Modifier
        .padding(0.dp, 0.dp, 0.dp, 0.dp)) {

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
                .height(100.dp)
            ) {

            }

        }
    }
}