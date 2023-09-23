package com.example.kmpsninotes.android.presentation.screen.note_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import com.example.kmpsninotes.android.until.InternetConnectionService

import com.example.kmpsninotes.android.until.TimeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository,
    private val internetConnectionService: InternetConnectionService
):ViewModel() {
    private val _uiState = MutableStateFlow(NoteDetailUiState())
    val uiState:StateFlow<NoteDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            if(savedStateHandle.get<String>("id") != "null"){
                val note = noteRepository.observeNoteById(savedStateHandle.get<String>("id")!!)
                _uiState.value = NoteDetailUiState(
                    title = note.title,
                    text = note.text,
                    color = note.color,
                    online_sync = note.online_sync,
                    timestamp = note.timestamp
                )
            }else{
                _uiState.value = NoteDetailUiState(
                    title = "Title",
                    text = "Write your note here...",
                    color = Note.generateRandom()
                )
            }
        }
    }

    fun onTitleFieldChange(title:String){
        _uiState.value = uiState.value.copy(title = title)
    }

    fun onTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(text = text)
    }

    fun databaseSync(){
        viewModelScope.launch {

            val noteId = if(savedStateHandle.get<String>("id") == "null") UUID.randomUUID().toString() else savedStateHandle.get<String>("id")!!
            val note = Note(
                id = noteId,
                title = _uiState.value.title,
                text = _uiState.value.text,
                color = _uiState.value.color,
                online_sync = false,
                timestamp = if(_uiState.value.timestamp == null) TimeService.getCurrentTimeInMilliseconds() else _uiState.value.timestamp!!
            )

            val serverSyncRes = noteRepository.noteSyncWithServer(note,internetConnectionService.isOnline())
            note.online_sync = serverSyncRes.data != null
            if(serverSyncRes.data != null && serverSyncRes.data != "Updated") note.id = serverSyncRes.data!!
            noteRepository.insertNote(note)
            _uiState.value = uiState.value.copy(noteHasBeenEdited = true)
        }
    }

}