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
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.macluczak.mywallet.ui.theme.Coral
import java.time.Month

@Composable
fun CalendarEntity(month: String, days: List<String>, daysName: List<String>){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Column(modifier = Modifier.padding(vertical = 7.dp)) {
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[0],
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontWeight = FontWeight.Thin
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Transparent)
                .padding(10.dp)
            ) {
            Text(
                modifier = Modifier
                    .align(Center),
                text = days[0].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )}

        }

        Column(modifier = Modifier.padding(vertical = 7.dp)) {
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[1],
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontWeight = FontWeight.Thin
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Transparent)
                .padding(10.dp)
            ) {
            Text(
                modifier = Modifier
                    .align(Center),
                text = days[1].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )}

        }


        Column(modifier = Modifier.padding(vertical = 7.dp) ) {
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[2],
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontWeight = FontWeight.Thin
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Transparent)
                .padding(10.dp)
            ) {
            Text(
                modifier = Modifier
                .align(Center),
                text = days[2].toString(),

                style = MaterialTheme.typography.body1,
                color = Color.White
            )}

        }



                Column(modifier = Modifier.padding(vertical = 7.dp)) {
                    Text(
                        modifier = Modifier
                            .align(CenterHorizontally),
                        text = daysName[3],
                        style = MaterialTheme.typography.body1,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                    
                    Spacer(modifier = Modifier.size(10.dp))

                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.White)
                        .padding(10.dp)
                    ) {

//MIDDLE
                            Text(
                                modifier = Modifier
                                    .align(Center),
                                text = days[3].toString(),
                                style = MaterialTheme.typography.body1,
                                color = Coral
                            )
                        }

                }









        Column(modifier = Modifier.padding(vertical = 7.dp)) {
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[4],
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontWeight = FontWeight.Thin
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Transparent)
                .padding(10.dp)
            ) {
            Text(
                modifier = Modifier
                    .align(Center),
                text = days[4].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )}

        }

        Column(modifier = Modifier.padding(vertical = 7.dp)) {
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[5],
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontWeight = FontWeight.Thin
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Transparent)
                .padding(10.dp)
            ) {

            Text(
                modifier = Modifier
                    .align(Center),
                text = days[5].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )}

        }

        Column(modifier = Modifier.padding(vertical = 7.dp)) {

            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = daysName[6],
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontWeight = FontWeight.Thin
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Transparent)
                .padding(10.dp)
            ) {


            Text(
                modifier = Modifier
                    .align(Center),
                text = days[6].toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )}

        }



    }

}