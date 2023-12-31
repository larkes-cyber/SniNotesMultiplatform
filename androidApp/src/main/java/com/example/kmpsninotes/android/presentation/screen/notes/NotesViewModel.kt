package com.example.kmpsninotes.android.presentation.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import com.example.domain.repository.UserRepository
import com.example.kmpsninotes.android.until.InternetConnectionService

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository,
    private val internetConnectionService: InternetConnectionService
):ViewModel() {

    private val _notesUiState = MutableStateFlow(NotesUiState())
    val notesUiState:StateFlow<NotesUiState> = _notesUiState

    private val _userUiState = MutableStateFlow(UserUiState())
    val userUiState:StateFlow<UserUiState> = _userUiState

    private val _refreshingUiState = MutableStateFlow(false)
    val refreshingUiState:StateFlow<Boolean> = _refreshingUiState


    init {
        observeNotes()
        observeUserData()
    }

    private fun observeNotes(){
        viewModelScope.launch {
            _notesUiState.value = NotesUiState(isLoading = true)
            _notesUiState.value = NotesUiState(notesList = noteRepository.observeNotes(), isLoading = false)
        }
    }

    private fun observeUserData(){
        viewModelScope.launch {
            val user = userRepository.getUserData()
            _userUiState.value = userUiState.value.copy(user = user)
        }
    }

    fun switchSelectingMode(bool:Boolean){
        _notesUiState.value = notesUiState.value.copy(selectingMode = bool)
    }

    fun selectNote(note: Note){
        val currentSelectedNotes = notesUiState.value.selectedNotes.toMutableList()
        currentSelectedNotes.add(note)
        _notesUiState.value = notesUiState.value.copy(selectedNotes = currentSelectedNotes)
    }
    fun unselectNote(note:Note){
        val currentSelectedNotes = notesUiState.value.selectedNotes.toMutableList()
        currentSelectedNotes.remove(note)
        _notesUiState.value = notesUiState.value.copy(selectedNotes = currentSelectedNotes, selectingMode = currentSelectedNotes.size != 0)
    }

    fun deleteNotes(){
        viewModelScope.launch {
            notesUiState.value.selectedNotes.forEach { note ->
                noteRepository.deleteNote(note, internetConnectionService.isOnline())
            }
            _notesUiState.value = NotesUiState()
            observeNotes()
        }
    }

    fun refreshData(){
        viewModelScope.launch {
            _refreshingUiState.value = true
            val res = noteRepository.notesSynchronization(internetConnectionService.isOnline())
            if(res.data != null){
                observeNotes()
            }
            _refreshingUiState.value = false
        }
    }

    fun quitApp(){
        viewModelScope.launch {
            userRepository.deleteUser()
            _userUiState.value = userUiState.value.copy(hasBeenQuit = true)
        }
    }

}