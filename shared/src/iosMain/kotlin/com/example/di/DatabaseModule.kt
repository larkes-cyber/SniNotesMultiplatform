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
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.serializer
import platform.Foundation.setValue
import kotlin.native.concurrent.SharedImmutable
import kotlin.native.concurrent.freeze
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class DatabaseModule {


    private val factory by lazy { DatabaseDriverFactory() }

    private val noteDatabaseDataSource: NoteDatabaseDataSource by lazy {
        NoteDatabaseDataSourceImpl(NoteDatabase(factory.createDriver()))
    }

    private val noteRemoteDataSource:NoteRemoteDataSource by lazy {
        NoteRemoteDataSourceImpl()
    }

    private val userDatabaseDataSource:UserDatabaseDataSource by lazy {
        UserDatabaseDataSourceImpl(NoteDatabase(factory.createDriver()))
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