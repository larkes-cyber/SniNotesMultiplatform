package com.example.kmpsninotes.android.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kmpsninotes.android.presentation.navigation.Screen
import com.example.kmpsninotes.android.presentation.theme.AppTheme
import com.example.kmpsninotes.android.until.Constants.SPLASH_SCREEN_TEXT

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.status){
        if(uiState.status != null) navController.navigate(uiState.status!!)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = SPLASH_SCREEN_TEXT,
                style = MaterialTheme.typography.h5.copy(color = AppTheme.colors.primaryTitleColor)
            )
            CircularProgressIndicator()
        }
    }



}