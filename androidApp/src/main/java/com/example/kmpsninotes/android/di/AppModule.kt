package com.example.kmpsninotes.android.di

import android.app.Application
import android.content.Context
import com.example.data.database.source.*
import com.example.data.local.DatabaseDriverFactory
import com.example.data.repository.DataNoteRepository
import com.example.data.remote.source.NoteRemoteDataSource
import com.example.data.remote.source.NoteRemoteDataSourceImpl
import com.example.data.remote.source.UserRemoteDataSource
import com.example.data.remote.source.UserRemoteDataSourceImpl
import com.example.data.repository.DataUserRepository
import com.example.domain.repository.NoteRepository
import com.example.domain.repository.UserRepository
import com.example.kmpsninotes.android.until.InternetConnectionService
import com.example.kmpsninotes.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideContext(@ApplicationContext context: Context):Context {
        return context
    }



    @Provides
    @Singleton
    fun provideNoteDatabaseDataSource(sqlDriver: SqlDriver): NoteDatabaseDataSource {
        return NoteDatabaseDataSourceImpl(NoteDatabase(sqlDriver))
    }

    @Provides
    fun provideInternetConnectionService(context: Context):InternetConnectionService = InternetConnectionService(context)


    @Provides
    @Singleton
    fun provideNoteRemoteDataSource():NoteRemoteDataSource{
        return NoteRemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource():UserRemoteDataSource{
        return UserRemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideUserDatabaseDataSource(sqlDriver: SqlDriver):UserDatabaseDataSource{
        return UserDatabaseDataSourceImpl(NoteDatabase(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDatabaseDataSource: NoteDatabaseDataSource,
        noteRemoteDataSource: NoteRemoteDataSource,
        userDatabaseDataSource: UserDatabaseDataSource
    ):NoteRepository{
        return DataNoteRepository(
            noteDatabaseDataSource = noteDatabaseDataSource,
            userDatabaseDataSource = userDatabaseDataSource,
            noteRemoteDataSource = noteRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userDatabaseDataSource: UserDatabaseDataSource
    ):UserRepository {
        return DataUserRepository(
            userDatabaseDataSource = userDatabaseDataSource,
            userRemoteDataSource = userRemoteDataSource
        )
    }




}