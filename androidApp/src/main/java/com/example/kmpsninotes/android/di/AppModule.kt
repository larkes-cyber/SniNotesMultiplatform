package com.example.kmpsninotes.android.di

import android.app.Application
import com.example.data.local.DatabaseDriverFactory
import com.example.data.repository.DataNoteRepository
import com.example.data.source.NoteDatabaseDataSource
import com.example.data.source.NoteDatabaseDataSourceImpl
import com.example.domain.repository.NoteRepository
import com.example.kmpsninotes.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDatabaseDataSource(sqlDriver: SqlDriver):NoteDatabaseDataSource{
        return NoteDatabaseDataSourceImpl(NoteDatabase(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabaseDataSource: NoteDatabaseDataSource):NoteRepository{
        return DataNoteRepository(noteDatabaseDataSource = noteDatabaseDataSource)
    }

}