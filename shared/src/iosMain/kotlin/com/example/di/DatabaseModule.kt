package com.example.di

import com.example.data.local.DatabaseDriverFactory
import com.example.data.database.source.NoteDatabaseDataSource
import com.example.data.database.source.NoteDatabaseDataSourceImpl
import com.example.data.database.source.UserDatabaseDataSource
import com.example.data.database.source.UserDatabaseDataSourceImpl
import com.example.data.remote.source.NoteRemoteDataSource
import com.example.data.remote.source.NoteRemoteDataSourceImpl
import com.example.data.remote.source.UserRemoteDataSource
import com.example.data.remote.source.UserRemoteDataSourceImpl
import com.example.data.repository.DataNoteRepository
import com.example.data.repository.DataUserRepository
import com.example.domain.repository.NoteRepository
import com.example.domain.repository.UserRepository
import com.example.kmpsninotes.database.NoteDatabase

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val db = NoteDatabase(factory.createDriver())
    private val noteDatabaseDataSource: NoteDatabaseDataSource by lazy {
        NoteDatabaseDataSourceImpl(db)
    }
    private val noteRemoteDataSource:NoteRemoteDataSource by lazy {
        NoteRemoteDataSourceImpl()
    }

    private val userDatabaseDataSource:UserDatabaseDataSource by lazy {
        UserDatabaseDataSourceImpl(db)
    }
    private val userRemoteDataSource:UserRemoteDataSource by lazy {
        UserRemoteDataSourceImpl()
    }

    val userRepository:UserRepository by lazy {
        DataUserRepository(
            userDatabaseDataSource = userDatabaseDataSource,
            userRemoteDataSource = userRemoteDataSource
        )
    }

    val noteRepository:NoteRepository by lazy {
        DataNoteRepository(
            noteDatabaseDataSource = noteDatabaseDataSource,
            noteRemoteDataSource = noteRemoteDataSource,
            userDatabaseDataSource = userDatabaseDataSource
        )
    }


}