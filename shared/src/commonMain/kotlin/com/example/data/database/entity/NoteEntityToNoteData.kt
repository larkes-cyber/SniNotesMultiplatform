package com.example.data.database.entity

import database.NoteEntity

fun NoteEntity.toNoteData(): NoteData {
    return NoteData(
        id = id,
        color = color,
        timestamp = timestamp,
        text = text,
        title = title,
        online_sync = true,
        visible = true
    )
}