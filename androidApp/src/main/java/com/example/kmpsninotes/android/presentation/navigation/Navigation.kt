package com.example.kmpsninotes.android.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kmpsninotes.android.presentation.screen.login.LoginScreen
import com.example.kmpsninotes.android.presentation.screen.login.LoginScreenViewModel
import com.example.kmpsninotes.android.presentation.screen.note_detail.NoteDetailScreen
import com.example.kmpsninotes.android.presentation.screen.note_detail.NoteDetailViewModel
import com.example.kmpsninotes.android.presentation.screen.notes.NotesScreen
import com.example.kmpsninotes.android.presentation.screen.notes.NotesViewModel
import com.example.kmpsninotes.android.presentation.screen.splash.SplashScreen
import com.example.kmpsninotes.android.presentation.screen.splash.SplashScreenViewModel

@Composable
fun Navigation(navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){

        composable(Screen.SplashScreen.route){
            val viewModel:SplashScreenViewModel = hiltViewModel()
            SplashScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.NotesScreen.route){
            val viewModel:NotesViewModel = hiltViewModel()
            NotesScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = Screen.NoteDetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    NavType.StringType
                    defaultValue = ""
                }
            )
        ){entry ->
            val viewModel:NoteDetailViewModel = hiltViewModel()
            val id = entry.arguments?.getString("id")
            NoteDetailScreen(
                viewModel = viewModel,
                id = id,
                navController = navController
            )
        }
        
        composable(
            route = Screen.LoginScreen.route
        ){
            val  viewModel:LoginScreenViewModel = hiltViewModel()
            LoginScreen(navController = navController, viewModel = viewModel)
        }

    }

}