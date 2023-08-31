package com.example.data.remote.model

import com.example.data.remote.model.NoteDto
import kotlinx.serialization.Serializable

@Serializable
data class NotesDto(
    val notes:List<NoteDto>
)