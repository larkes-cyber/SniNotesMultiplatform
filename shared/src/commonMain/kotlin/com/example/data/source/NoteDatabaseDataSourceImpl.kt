package com.example.data.source

import com.example.data.entity.NoteData
import com.example.data.entity.toNoteData
import com.example.kmpsninotes.database.NoteDatabase

class NoteDatabaseDataSourceImpl(
   private val db: NoteDatabase
):NoteDatabaseDataSource {

    private val queries = db.noteQueries

    override suspend fun insertNote(noteData: NoteData) {
        queries.insertNote(
            id = noteData.id,
            color = noteData.color,
            title = noteData.title,
            text = noteData.text,
            timestamp = noteData.timestamp,
            visible = noteData.visible,
            onlinesync = noteData.online_sync
        )
    }

    override suspend fun observeNotes(): List<NoteData> {
        return queries.observeNotes().executeAsList().map { it.toNoteData() }
    }

    override suspend fun observeNoteById(id: String): NoteData {
        return queries.observeNoteById(id).executeAsOneOrNull()?.toNoteData()!!
    }

    override suspend fun deleteNote(noteData: NoteData) {
    }

    override suspend fun nukeNotes() {
        queries.nukeNotes()
    }
}