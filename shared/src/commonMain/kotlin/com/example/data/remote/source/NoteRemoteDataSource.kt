package com.example.data.remote.source

import com.example.data.remote.model.NoteDto
import com.example.domain.model.Note
import com.example.domain.model.User
import com.example.until.Constants
import com.example.until.Resource

interface NoteRemoteDataSource {

    suspend fun pushNote(note: NoteDto, user: User):Resource<String>
    suspend fun updateNote(note: NoteDto, user:User):Resource<String>
    suspend fun deleteNote(note: NoteDto, user: User):Resource<String>
    suspend fun findNote(note:NoteDto, user: User):Resource<String>
    suspend fun observeNotes(user: User):Resource<List<NoteDto>>

    companion object{
        const val BASE_URL = Constants.SNI_NOTES_URL
    }

    sealed class Endpoints(val url: String){
        object Notes: Endpoints("$BASE_URL/notes")
        object SingleNote: Endpoints("$BASE_URL/singleNote")
    }

}