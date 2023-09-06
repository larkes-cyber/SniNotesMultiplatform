package com.example.data.remote.model

import com.example.data.remote.model.NoteDto
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class NotesDto(
    val notes:List<NoteDto>
)