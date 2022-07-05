package com.macluczak.mywallet

sealed class Screen (val route: String){
    object HomeScreen: Screen("home_screen")
    object NoteDetail: Screen("note_detail")
    object TaskDetail: Screen("task_detail")


    fun withArgs(vararg args: Int): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}