package com.example.data.remote.source
import com.example.data.remote.model.NoteDto
import com.example.data.remote.model.NotesDto
import com.example.domain.model.User
import com.example.until.Resource
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.BodyProgress.Plugin.install
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

class NoteRemoteDataSourceImpl(
):NoteRemoteDataSource {

    private val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json()
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
                body = note
            }
            Resource.Success(response.bodyAsText())
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
                body = note
            }
            Resource.Success("ok")
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
            Resource.Success("Ok")
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
            Resource.Success("Ok")
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
            Resource.Success(notes.notes)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }
}