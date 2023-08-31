package com.example.kmpsninotes.android.presentation.screen.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.NoteRepository
import com.example.domain.repository.UserRepository
import com.example.kmpsninotes.android.presentation.navigation.Screen
import com.example.kmpsninotes.android.until.InternetConnectionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository,
    private val internetConnectionService: InternetConnectionService
):ViewModel() {

    private val _uiState = MutableStateFlow(SplashScreenState())
    val uiState:StateFlow<SplashScreenState> = _uiState

    init {
        viewModelScope.launch {
            val savedUserData = userRepository.getUserData()
            if(savedUserData == null) _uiState.value = SplashScreenState(status = Screen.LoginScreen.route)
            else {
                _uiState.value = SplashScreenState(isLoading = true)
                noteRepository.notesSynchronization(internetConnectionService.isOnline())
                _uiState.value = SplashScreenState(status = Screen.NotesScreen.route)
            }
        }
    }

}