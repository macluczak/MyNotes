package com.macluczak.mywallet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.ui.theme.BlueNote
import com.macluczak.mywallet.ui.theme.BlueNoteLight
import com.macluczak.mywallet.ui.theme.BlueNoteMedium
import com.macluczak.mywallet.ui.theme.TransparentRed
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun NoteItem(item: Note, viewModel: MainViewModel) {

    Box(modifier = Modifier
        .padding(15.dp, 0.dp, 0.dp, 0.dp)) {

        var visible = remember { mutableStateOf(false) }
        var color = remember { mutableStateOf(TransparentRed) }
        val editNoteLiveData = viewModel.getNoteEdit().observeAsState()

        BoxWithConstraints(modifier = Modifier

            .clip(RoundedCornerShape(15.dp))
            .height(160.dp)
            .aspectRatio(1f)
            .background(BlueNote)
//            .pointerInput(Unit){
//                detectTapGestures(onLongPress = {

//                })           }
            .clickable {

                when (visible.value) {
                    true -> visible.value = false
                    false -> visible.value = true
                }

//            viewModel.deleteNote(item)


            }

        ) {

            val height = constraints.maxHeight
            val width = constraints.maxWidth

            val mediumTonePoint1 = Offset(0f, height * 0.3f)
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

            val lightTonePoint1 = Offset(0f, height * 0.35f)
            val lightTonePoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightTonePoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightTonePoint4 = Offset(width * 0.65f, height.toFloat())
            val lightTonePoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

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
                .fillMaxSize()) {

                drawPath(path = mediumTonePath,
                    color = BlueNoteMedium)

                drawPath(path = lightTonePath,
                    color = BlueNoteLight)


            }


            Row(
                modifier = Modifier
                    .padding(5.dp, 5.dp, 15.dp, 15.dp)
                    .fillMaxWidth()
            )
            {
                Text(modifier = Modifier

                    .clickable {
                        val newNote = item.copy(title = "UPDEJCIOR")
//                    viewModel.updateNote(newNote)
                    }
                    .padding(horizontal = 15.dp)
                    .align(Alignment.CenterVertically),
                    text = item.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Text(modifier = Modifier
                .padding(15.dp, 15.dp, 10.dp, 10.dp)
                .align(Alignment.BottomEnd),
                text = item.id.toString(),
                fontSize = 16.sp,
                color = Color.White)

            if (editNoteLiveData.value == true) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color.value)
                    .clickable {
                        viewModel.deleteNote(item)
                    }
                )
            }
        }


    }


}

@Composable
fun DisplayNotes(notes: List<Note>, viewModel: MainViewModel) {

    LazyRow {
        items(notes.size) { index ->
            NoteItem(item = notes[index], viewModel = viewModel)
        }
    }


}