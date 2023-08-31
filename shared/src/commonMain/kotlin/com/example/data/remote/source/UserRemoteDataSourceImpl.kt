package com.example.data.remote.source

import com.example.data.remote.model.UserDataDto
import com.example.data.remote.model.UserDto
import com.example.domain.mapper.toLoginDto
import com.example.domain.mapper.toUser
import com.example.domain.mapper.toUserDto

import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.until.Resource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString


class UserRemoteDataSourceImpl(): UserRemoteDataSource {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun registerUser(user: User): Resource<User> {
        return try {
            val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Register.url){
                contentType(ContentType.Application.Json)
                body = user.toUserDto()
            }
            val session = response.bodyAsText()
            Resource.Success(User(
                name = user.name,
                password = user.password,
                session = session,
                login = user.login
            ))
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }

    }

    override suspend fun authorizationUser(login: Login): Resource<String> {
        return try {
            val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Authorization.url){
                contentType(ContentType.Application.Json)
                body = login.toLoginDto()
            }
            val session = response.bodyAsText()
            Resource.Success(session)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun observeUser(session: String, email: String): Resource<User> {
        return try {
            val response:HttpResponse = client.get(UserRemoteDataSource.BASE_URL){
                url{
                    parameters.append("session", session)
                    parameters.append("email", email)
                }
            }
            val userDto = Json.decodeFromString<UserDataDto>(response.bodyAsText())
            Resource.Success(userDto.toUser())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }


}