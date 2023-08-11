package com.example.kmpsninotes.android.presentation.screen.notes

import com.example.domain.model.Note


data class NotesUiState(
    val isLoading:Boolean = false,
    val notesList:List<Note> = listOf(),
    val error:String = "",
    val selectingMode:Boolean = false,
    val selectedNotes:List<Note> = listOf()
)