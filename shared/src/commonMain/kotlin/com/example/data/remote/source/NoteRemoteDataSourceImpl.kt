package com.example.data.remote.source
import com.example.data.remote.model.NoteDto
import com.example.data.remote.model.NotesDto
import com.example.domain.model.User
import com.example.until.Resource
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

class NoteRemoteDataSourceImpl(
):NoteRemoteDataSource {

    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    override suspend fun pushNote(note: NoteDto, user: User): Resource<String> {

        return try {
            val response: HttpResponse = client.post(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
                setBody(note)
            }
            if(response.status.value == 200) Resource.Success(response.bodyAsText())
            else Resource.Error(response.bodyAsText())
        }catch (e:Exception) {
            Resource.Error(e.message.toString())
        }

    }

    override suspend fun updateNote(note: NoteDto, user: User): Resource<String> {
        return try {
            val response: HttpResponse = client.put(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
                setBody(note)
            }
            if(response.status.value == 200) Resource.Success("ok")
            else Resource.Error(response.bodyAsText())
        }catch (e:Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun deleteNote(note: NoteDto, user: User): Resource<String> {
        return try {
            val response:HttpResponse = client.delete(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("id", note.id!!)
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
            }
            println(response.bodyAsText())
            if(response.status.value == 200) Resource.Success("Ok")
            else Resource.Error(response.bodyAsText())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun findNote(note: NoteDto, user: User): Resource<String> {
        return try {
            val response:HttpResponse = client.get(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("id", note.id ?: "")
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
            }
            if(response.status.value == 200) Resource.Success("Ok")
            else Resource.Error(response.bodyAsText())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun observeNotes(user: User): Resource<List<NoteDto>> {
        return try {
            val response:HttpResponse = client.get(NoteRemoteDataSource.Endpoints.Notes.url){
                url {
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
            }
            val notes = Json.decodeFromString<NotesDto>(response.bodyAsText())

            if(response.status.value == 200) Resource.Success(notes.notes)
            else Resource.Error(response.bodyAsText())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }
}