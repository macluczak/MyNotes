package com.macluczak.mywallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import com.macluczak.mywallet.ui.theme.GreenDone
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun TaskItem(item: Task, viewModel: MainViewModel, navController: NavController) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp, 5.dp, 15.dp, 0.dp)
        .height(60.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(color = Color.LightGray)
        .clickable {
            navController.navigate(Screen.TaskDetail.withArgs(item.id))
        }

    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                ,Arrangement.SpaceBetween
        )
        {

            Text(modifier = Modifier
                .clickable {
                    val newNote = item.copy(title = "UPDEJCIOR")
//                    viewModel.updateNote(newNote)
                }
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically),
                text = item.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )


            if (viewModel.markAsDone.value == true){
                Box(modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxHeight()
                    .background(GreenDone)

                ){
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_check_24), contentDescription = "Done",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)
                        , tint = Color.White)
                }

            }


        }
//        Text(modifier = Modifier
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