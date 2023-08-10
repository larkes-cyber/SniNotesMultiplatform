package com.example.data.repository

import com.example.data.source.NoteDatabaseDataSource
import com.example.domain.mapper.toNote
import com.example.domain.mapper.toNoteData
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import com.example.until.Resource


class DataNoteRepository(
    private val noteDatabaseDataSource: NoteDatabaseDataSource
):NoteRepository {

    override suspend fun insertNote(noteEntity: Note) {
        noteDatabaseDataSource.insertNote(noteEntity.toNoteData())
    }

    override suspend fun observeNotes(): List<Note> {
        return noteDatabaseDataSource.observeNotes().map { it.toNote() }
    }

    override suspend fun observeNoteById(id: String): Note {
        return noteDatabaseDataSource.observeNoteById(id).toNote()
    }

    override suspend fun deleteNote(noteEntity: Note) {
        noteDatabaseDataSource.deleteNote(noteEntity.toNoteData())
    }

}