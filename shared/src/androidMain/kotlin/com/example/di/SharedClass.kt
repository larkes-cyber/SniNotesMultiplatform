package com.example.di

import com.example.data.remote.source.NoteRemoteDataSource
import com.example.data.remote.source.NoteRemoteDataSourceImpl
import com.example.data.remote.source.UserRemoteDataSource
import com.example.data.remote.source.UserRemoteDataSourceImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class SharedClass {

    val noteRemoteDataSource: NoteRemoteDataSource by lazy {
        NoteRemoteDataSourceImpl()
    }

    val userRemoteDataSource: UserRemoteDataSource by lazy {
        UserRemoteDataSourceImpl()
    }

}