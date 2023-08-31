package com.example.domain.mapper

import com.example.data.database.entity.NoteData
import com.example.data.remote.model.NoteDto
import com.example.domain.model.Note

fun Note.toNoteData(): NoteData {
    return NoteData(
        id = id,
        color = color,
        timestamp = timestamp,
        text = text,
        title = title,
        online_sync = online_sync,
        visible = visible
    )
}

fun NoteData.toNote():Note{
    return Note(
        id = id,
        color = color,
        timestamp = timestamp,
        text = text,
        title = title,
        online_sync = online_sync,
        visible = visible
    )
}

fun Note.toNoteDto() = NoteDto(
    title = title,
    id = id?.toString(),
    text = text,
    color = color,
    timestamp = timestamp
)
fun NoteDto.toNote() = Note(
    title = title,
    id = id!!,
    text = text,
    color = color,
    online_sync = true,
    timestamp = timestamp
)