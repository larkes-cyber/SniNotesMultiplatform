package com.example.data.source

import com.example.data.entity.NoteData

interface NoteDatabaseDataSource {
    suspend fun insertNote(noteData: NoteData)
    suspend fun observeNotes():List<NoteData>
    suspend fun observeNoteById(id:String):NoteData
    suspend fun deleteNote(noteData: NoteData)
    suspend fun nukeNotes()
}