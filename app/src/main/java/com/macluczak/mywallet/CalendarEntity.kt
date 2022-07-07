package com.macluczak.mywallet

import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Month

@Composable
fun CalendarEntity(month: String, days: List<String>, daysName: List<String>){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Column(modifier = Modifier.padding(vertical = 7.dp)) {

            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = days[0].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[0],
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

        Column(modifier = Modifier.padding(vertical = 7.dp)) {

            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = days[1].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[1],
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }


        Column(modifier = Modifier.padding(vertical = 7.dp) ) {

            Text(
                modifier = Modifier
                .align(CenterHorizontally),
                text = days[2].toString(),

                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[2],
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }


            Box(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(7.dp)
            ){
                Column() {
//MIDDLE
                    Text(
                        modifier = Modifier
                            .align(CenterHorizontally),
                        text = days[3].toString(),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .align(CenterHorizontally),
                        text = daysName[3],
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                }



            }





        Column(modifier = Modifier.padding(vertical = 7.dp)) {

            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = days[4].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[4],
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

        Column(modifier = Modifier.padding(vertical = 7.dp)) {

            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = days[5].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[5],
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

        Column(modifier = Modifier.padding(vertical = 7.dp)) {


            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = days[6].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[6],
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }



    }

}