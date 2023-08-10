package com.example.kmpsninotes.android.presentation.navigation

sealed class Screen(val route:String){

    object LoginScreen:Screen("login")
    object SplashScreen:Screen("splash")
    object NotesScreen:Screen("notes")
    object NoteDetailScreen:Screen("note_detail")
    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}