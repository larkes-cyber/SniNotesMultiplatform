package com.example.di

import ` com`.example.kmpsninotes.database.NoteDatabase
import com.example.data.local.DatabaseDriverFactory
import com.example.data.database.source.NoteDatabaseDataSource
import com.example.data.database.source.NoteDatabaseDataSourceImpl

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDatabaseDataSource by lazy {
        NoteDatabaseDataSourceImpl(NoteDatabase(factory.createDriver()))
    }

}