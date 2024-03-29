package com.macluczak.mywallet.bottom_menu

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.macluczak.mywallet.R
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun fab(viewModel: MainViewModel, navController: NavController, onFabClick: () -> Unit) {

        FloatingActionButton(
            onClick = {
                onFabClick()
                viewModel.routeState.value = Screen.CreateScreen.route
                navController.navigate(Screen.CreateScreen.withArgs()) },
            backgroundColor = Coral


        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "fab",
                modifier = Modifier
                    .size(30.dp),
                tint = Color.White
            )
        }

}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = HippieBlue,
    activeTextColor: Color = BlueNote,
    activeIconColor: Color = Color.White,
    inactiveTextColor: Color = HippieBlue300,
    initialSelectedItemIndex: Int = 0,
    navController: NavController,
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    when(navController.currentDestination?.route){
        Screen.HomeScreen.route -> selectedItemIndex = 0
        Screen.SecondPage.route -> selectedItemIndex = 1
        Screen.ThirdPage.route -> selectedItemIndex = 2
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(30.dp, 0.dp, 80.dp, 0.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                activeIconColor = activeIconColor,
                inactiveTextColor = inactiveTextColor,
                navController = navController
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = BlueNote,
    activeTextColor: Color = BlueNote,
    activeIconColor: Color = Color.White,
    inactiveTextColor: Color = Color.LightGray,
    navController: NavController,
    onItemClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                if (navController.currentDestination?.route != item.route) {

                    navController.popBackStack(navController.currentDestination?.route!!, true)
                    when (item.route) {
                        "second_page" -> navController.navigate(Screen.SecondPage.withArgs())
                        "home_screen" -> navController.navigate(Screen.HomeScreen.withArgs())
                        "third_page" -> navController.navigate(Screen.ThirdPage.withArgs())
                    }

                }


                onItemClick()
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeIconColor else inactiveTextColor,
                modifier = Modifier.size(30.dp)
            )
        }
//        Text(
//            text = item.title,
//            color = if (isSelected) activeTextColor else inactiveTextColor
//        )
    }
}