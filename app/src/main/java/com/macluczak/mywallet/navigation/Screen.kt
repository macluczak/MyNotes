package com.macluczak.mywallet.navigation

sealed class Screen (val route: String){
    object HomeScreen: Screen("home_screen")
    object NoteDetail: Screen("note_detail")
    object TaskDetail: Screen("task_detail")
    object SecondPage: Screen("second_page")
    object ThirdPage: Screen("third_page")
    object CreateScreen: Screen("create_screen")


    fun withArgs(vararg args: Int): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}