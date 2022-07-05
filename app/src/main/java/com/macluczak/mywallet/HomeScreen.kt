package com.macluczak.mywallet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlueNote)
    ) {
        Column {

            Calendar()
            CardViewHome(viewModel)


        }

    }
}


@Composable
fun Calendar() {
    var month = "September"
    var days = listOf(4, 5, 6, 7, 8, 9, 10)
    var daysName = listOf("Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun")

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
fun CardViewHome(viewModel: MainViewModel) {

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

            var editNoteText = remember { mutableStateOf("Edit") }

            SearchView()

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


            DisplayNotes(notes = listNote, viewModel = viewModel)
            DisplayTasks(tasks = listTask, viewModel = viewModel)

        }


    }


}

@Composable
fun SearchView() {
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
                onValueChange = { mText = it },
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

