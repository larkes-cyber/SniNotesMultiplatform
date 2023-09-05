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
    val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    val noteRemoteDataSource: NoteRemoteDataSource by lazy {
        NoteRemoteDataSourceImpl(client)
    }

    val userRemoteDataSource: UserRemoteDataSource by lazy {
        UserRemoteDataSourceImpl(client)
    }

}