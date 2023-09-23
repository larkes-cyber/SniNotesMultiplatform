package com.example.data.repository

import com.example.data.database.source.NoteDatabaseDataSource
import com.example.data.database.source.UserDatabaseDataSource
import com.example.data.remote.source.NoteRemoteDataSource
import com.example.domain.mapper.toNote
import com.example.domain.mapper.toNoteData
import com.example.domain.mapper.toNoteDto
import com.example.domain.mapper.toUser
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import com.example.until.Resource


class DataNoteRepository(
    private val noteDatabaseDataSource: NoteDatabaseDataSource,
    private val noteRemoteDataSource: NoteRemoteDataSource,
    private val userDatabaseDataSource: UserDatabaseDataSource
):NoteRepository {

    override suspend fun insertNote(noteEntity: Note) {
        println(noteEntity.id + "#$%^&*(*&^%$%^&*(")
        noteDatabaseDataSource.insertNote(noteEntity.toNoteData())
    }

    override suspend fun observeNotes(): List<Note> {
        return noteDatabaseDataSource.observeNotes().map { it.toNote() }
    }

    override suspend fun observeNoteById(id: String): Note {
        return noteDatabaseDataSource.observeNoteById(id).toNote()
    }

    override suspend fun deleteNote(noteEntity: Note, online: Boolean) {
        if(online) {
            val serverResponse = noteRemoteDataSource.deleteNote(
                note = noteEntity.toNoteDto(),
                user = userDatabaseDataSource.observeUser()!!.toUser()
            )
            noteDatabaseDataSource.deleteNote(noteEntity.toNoteData())
        }else{
            noteEntity.visible = false
            insertNote(noteEntity)
        }
    }

    override suspend fun pushNote(note: Note): Resource<String> = noteRemoteDataSource.pushNote(
        note = note.toNoteDto(),
        user = userDatabaseDataSource.observeUser()!!.toUser()
    )

    override suspend fun updateNote(note: Note): Resource<String> = noteRemoteDataSource.updateNote(
        note = note.toNoteDto(),
        user = userDatabaseDataSource.observeUser()!!.toUser()
    )

    override suspend fun noteSyncWithServer(note: Note, online: Boolean):Resource<String> {
        if(online) {
            try {
                val findNoteRes = noteRemoteDataSource.findNote(
                    note = note.toNoteDto(),
                    user = userDatabaseDataSource.observeUser()!!.toUser()
                )
                if (findNoteRes.message == null) {
                    updateNote(note)
                }
                else {
                    val id = pushNote(note = note)
                    println(note.id + "^&*()_+dfdbfvcsedfbfdffsd")
                    noteDatabaseDataSource.deleteNote(note.toNoteData())
                    return Resource.Success(id.data)
                }
                return Resource.Success("Updated")
            } catch (e: Exception) {
                return Resource.Error(e.message.toString())
            }
        }else{
            return Resource.Error("No internet connection")
        }
    }

    override suspend fun notesSynchronization(online:Boolean): Resource<String> {
        if(online){
            val allNotes = observeNotes()


            allNotes.filter { !it.visible }.forEach {
                println("###############################")
                deleteNote(it, online)
            }
            allNotes.filter { !it.online_sync }.forEach {
                noteSyncWithServer(it, online)
            }
            noteDatabaseDataSource.nukeNotes()

            val notesFromServer = noteRemoteDataSource.observeNotes(userDatabaseDataSource.observeUser()!!.toUser())
            if(notesFromServer.data == null) return Resource.Error(notesFromServer.message!!)
            notesFromServer.data.forEach { insertNote(it.toNote()) }

            return Resource.Success("Success")
        }else {
            return Resource.Error("No internet connection")
        }
    }

}