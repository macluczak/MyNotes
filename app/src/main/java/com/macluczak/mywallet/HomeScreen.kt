package com.macluczak.mywallet

import android.view.Display
import android.widget.EditText
import android.widget.SearchView
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.ui.theme.BlueNote
import com.macluczak.mywallet.ui.theme.MyWalletTheme
import com.macluczak.mywallet.viewmodels.NoteViewModel

@Composable
fun HomeScreen(viewModel: NoteViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlueNote)
    ){
        Column {

            Calendar()
            CardViewHome(viewModel)


        }

    }
}


@Composable
fun Calendar() {
    var month = "September"
    var days = listOf(4,5,6,7,8,9,10)
    var daysName = listOf("Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun")

    Column(modifier = Modifier
        .fillMaxWidth()
        ) {

        Text(

            style = MaterialTheme.typography.h3,
            text = month,
            color = Color.White

        )

        CalendarEntity(month = month, days = days, daysName = daysName)




    }
}


@Composable
fun CardViewHome(viewModel: NoteViewModel){

    val listNoteLiveData = viewModel.getAllNotes()
    val listNote by listNoteLiveData.observeAsState(initial = emptyList())

    Card(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
        .background(Color.White),
        elevation = 7.dp
    ){

        Column() { 
            SearchView()
           DisplayNotes(notes = listNote, viewModel =  viewModel)

        }




    }


}

@Composable
fun NoteItem(item: Note){
    
    BoxWithConstraints(modifier = Modifier
        .aspectRatio(1f)
        .clip(RoundedCornerShape(15.dp))
        .height(30.dp)
        .background(BlueNote)
        .clickable {
//            viewModel.deleteNote(item)

        }

    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
        {
            Text( modifier = Modifier
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
        }
        Text( modifier = Modifier
            .padding(horizontal = 16.dp)
            .align(Alignment.CenterEnd),
            text = item.id.toString(),
            fontSize = 16.sp)
    }
    
}

@Composable
fun DisplayNotes(notes: List<Note>, viewModel: NoteViewModel){

    LazyRow{
        items(notes.size){ index ->
            ListItem(item = notes[index], viewModel = viewModel)
        }
    }



}




@Composable
fun SearchView(){
    Box(modifier = Modifier
        .fillMaxWidth()

        .background(Color.Green)
        .padding(15.dp)){

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(BlueNote)
        ){

            var mText by remember { mutableStateOf("") }
            TextField(value = mText ,
                onValueChange = {mText = it},
                label = {Text("Search...")},
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
        
        NoteItem(item = Note("Note1", "msg", 1, 1))
        

    }
}

