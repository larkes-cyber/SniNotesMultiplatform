package com.example.kmpsninotes.android.presentation.screen.note_detail

import java.sql.Timestamp

data class NoteDetailUiState(
    val title:String = "Title",
    val text:String = "Write your note...",
    val online_sync:Boolean = false,
    val timestamp: Long? = null,
    val noteHasBeenEdited:Boolean = false,
    val color:Long = 0L
)