package com.macluczak.mywallet

import android.view.Display
import android.widget.EditText
import android.widget.SearchView
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.NoteViewModel
import java.time.temporal.TemporalQueries.offset

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

    Box(modifier = Modifier
        .padding(15.dp, 0.dp,0.dp,0.dp)){


        BoxWithConstraints(modifier = Modifier

            .clip(RoundedCornerShape(15.dp))
            .height(160.dp)
            .aspectRatio(1f)
            .background(BlueNote)
            .clickable {
//            viewModel.deleteNote(item)

            }

        ){

            val height = constraints.maxHeight
            val width = constraints.maxWidth

            val mediumTonePoint1 = Offset(0f, height*0.3f)
            val mediumTonePoint2 = Offset(width * 0.1f, height * 0.33f)
            val mediumTonePoint3 = Offset(width * 0.4f, height * 0.05f)
            val mediumTonePoint4 = Offset(width * 0.75f, height * 0.7f)
            val mediumTonePoint5 = Offset(width * 1.5f, -height.toFloat())

            val mediumTonePath = Path().apply {

                moveTo(mediumTonePoint1.x, mediumTonePoint1.y)

                calcFromTo(mediumTonePoint1, mediumTonePoint2)
                calcFromTo(mediumTonePoint2, mediumTonePoint3)
                calcFromTo(mediumTonePoint3, mediumTonePoint4)
                calcFromTo(mediumTonePoint4, mediumTonePoint5)

                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()

            }

            val lightTonePoint1  = Offset(0f, height*0.35f)
            val lightTonePoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightTonePoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightTonePoint4 = Offset(width * 0.65f, height.toFloat())
            val lightTonePoint5 = Offset(width * 1.4f, -height.toFloat()/ 3f)

            val lightTonePath = Path().apply {

                moveTo(lightTonePoint1.x, lightTonePoint1.y)

                calcFromTo(lightTonePoint1, lightTonePoint2)
                calcFromTo(lightTonePoint2, lightTonePoint3)
                calcFromTo(lightTonePoint3, lightTonePoint4)
                calcFromTo(lightTonePoint4, lightTonePoint5)

                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()

            }
            
            Canvas(modifier = Modifier
                .fillMaxSize()){

                drawPath(path = mediumTonePath,
                color =  BlueNoteMedium)

                drawPath(path = lightTonePath,
                    color =  BlueNoteLight)


            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
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
                .padding(horizontal = 15.dp)
                .align(Alignment.CenterEnd),
                text = item.id.toString(),
                fontSize = 16.sp)
        }



    }



}

@Composable
fun DisplayNotes(notes: List<Note>, viewModel: NoteViewModel){

    LazyRow{
        items(notes.size){ index ->
            NoteItem(item = notes[index])
        }
    }



}




@Composable
fun SearchView(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)){

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
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

