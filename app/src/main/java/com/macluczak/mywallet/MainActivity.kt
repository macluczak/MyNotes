package com.macluczak.mywallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.ui.theme.MyWalletTheme
import com.macluczak.mywallet.viewmodels.MainViewModel

class MainActivity : ComponentActivity(){
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            MyWalletTheme {
                viewModel = ViewModelProvider
                    .AndroidViewModelFactory
                    .getInstance(application)
                    .create(MainViewModel:: class.java)

                val listNoteLiveData = viewModel.getAllNotes()
                val listNote by listNoteLiveData.observeAsState(initial = emptyList())

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    if(listNote.isNotEmpty()){
//                        viewModel.insertTask(Task("Zadanie 1","task", 1, 1, 1))
//                        viewModel.insertTask(Task("Dom 2","task", 1, 1, 1))

//                        DisplayList(notes = listNote, viewModel= viewModel)
                       Navigation(viewModel = viewModel)


                    }else{
                        Loading(viewModel = viewModel)

                    }



                }
            }
        }
    }
}

@Composable
fun Loading(viewModel: MainViewModel){
    Surface(modifier = Modifier
        .fillMaxSize()
        .clickable {
            viewModel.insertNote(Note("Note1", "msg", 1, 1))
            viewModel.insertNote(Note("Note2", "msg", 1, 1))
            viewModel.insertNote(Note("Note3", "msg", 1, 1))
            viewModel.insertNote(Note("Note4", "msg", 1, 1))
            viewModel.insertNote(Note("Note5", "msg", 1, 1))
            viewModel.insertNote(Note("Note6", "msg", 1, 1))
            viewModel.insertNote(Note("Note7", "msg", 1, 1))
            viewModel.insertNote(Note("Note8", "msg", 1, 1))
            viewModel.insertNote(Note("Note9", "msg", 1, 1))
            viewModel.insertNote(Note("Note10", "msg", 1, 1))
            viewModel.insertNote(Note("Note11", "msg", 1, 1))
            viewModel.insertNote(Note("Note12", "msg", 1, 1))
            viewModel.insertNote(Note("Note13", "msg", 1, 1))
            viewModel.insertNote(Note("Note14", "msg", 1, 1))
            viewModel.insertNote(Note("Note15", "msg", 1, 1))
        },
        color = MaterialTheme.colors.background) {
        Text(modifier = Modifier.fillMaxSize(),
        text = "Nothing is hear",
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        )
//
//        LottieAnimation(
//            composition,
//            progress,
//            modifier = Modifier.size(400.dp)
//        )

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyWalletTheme {

    }
}