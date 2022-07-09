package com.macluczak.mywallet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlueNote)
    ) {
        Column {

            Calendar(viewModel = viewModel)
            CardViewHome(viewModel = viewModel, navController = navController)



        }


    }
}


@Composable
fun Calendar(viewModel: MainViewModel) {
    var month = viewModel.nowMonth
    var days = viewModel.listOfDay
    Log.d("Days", days.toString())
    var daysName = viewModel.listofWeek

    Column(modifier = Modifier
        .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            style = MaterialTheme.typography.h3,
            text = month,
            color = Color.White


        )

        CalendarEntity(month = month, days = days, daysName = daysName)


    }
}


@Composable
fun CardViewHome(viewModel: MainViewModel, navController: NavController) {

    val listNoteLiveData = viewModel.getAllNotes()
    val listNote by listNoteLiveData.observeAsState(initial = emptyList())

    val listTaskLiveData = viewModel.getAllTasks()
    val listTask by listTaskLiveData.observeAsState(initial = emptyList())

    Card(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
        .background(Color.White),
        elevation = 7.dp
    ) {

        Column() {

            val editNoteText = remember { mutableStateOf("Edit") }


            if(viewModel.getNoteEdit().value == true){
                editNoteText.value = "Cancel"
            }


            SearchView(viewModel = viewModel)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "My Notes",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )

                Text(text = editNoteText.value,
                    style = MaterialTheme.typography.body1,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable {

                            if (viewModel.getNoteEdit().value == false) {
                                viewModel.noteEditable()
                                editNoteText.value = "Cancel"

                            } else {
                                viewModel.noteUneditable()
                                editNoteText.value = "Edit"
                            }

                        }

                )
            }


            DisplayNotes(notes = listNote, viewModel = viewModel, navController = navController)

            val markTaskText = remember { mutableStateOf("Mark as Done") }
            if(viewModel.getMarkTask().value == true){
                markTaskText.value = "Cancel"
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 15.dp, 15.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "In Progress",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )

                Text(text = markTaskText.value,
                    style = MaterialTheme.typography.body1,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable {

                            if (viewModel.markAsDone.value == false) {
                                viewModel.asDone()
                                markTaskText.value = "Cancel"

                            } else {
                                viewModel.asDoneCancel()
                                markTaskText.value = "Mark as Done"
                            }

                        }

                )
            }

            DisplayTasks(tasks = listTask, viewModel = viewModel, navController = navController)

        }



    }


}

@Composable
fun SearchView(viewModel: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp, 15.dp, 15.dp, 15.dp)) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
        ) {

            var mText by remember { mutableStateOf("") }

            TextField(value = mText,
                onValueChange = {
                    mText = it
                    viewModel.searchWord.value = it
                },
                label = { Text("Search...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)

            )

        }


    }

}

@Preview()
@Composable
fun HomePreview() {
    MyWalletTheme {




    }
}

