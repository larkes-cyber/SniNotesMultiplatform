package com.example.domain.mapper

import com.example.data.entity.NoteData
import com.example.domain.model.Note

fun Note.toNoteData():NoteData{
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