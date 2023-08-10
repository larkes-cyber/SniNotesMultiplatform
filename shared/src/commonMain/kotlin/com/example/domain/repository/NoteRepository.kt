package com.example.domain.repository

import com.example.domain.model.Note
import com.example.until.Resource
import database.NoteEntity

interface NoteRepository {
    suspend fun insertNote(noteEntity: Note)
    suspend fun observeNotes():List<Note>
    suspend fun observeNoteById(id:String): Note
    suspend fun deleteNote(noteEntity: Note)
}