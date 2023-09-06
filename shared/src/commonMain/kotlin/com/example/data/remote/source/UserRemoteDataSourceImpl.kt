package com.example.data.remote.source

import com.example.data.remote.http_client.httpClient
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
class UserRemoteDataSourceImpl(
): UserRemoteDataSource {


    private val client = httpClient(){
        install(ContentNegotiation){
            json(Json{
                prettyPrint = true
                isLenient = true
            })
        }
    }
    override suspend fun registerUser(user: User): Resource<User> {
        return try {
            val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Register.url){
                contentType(ContentType.Application.Json)
                setBody(user.toUserDto())
            }
            val session = response.bodyAsText()

            if(response.status.value == 200) {
                Resource.Success(
                    User(
                        name = user.name,
                        password = user.password,
                        session = session,
                        login = user.login
                    )
                )
            }
            else  Resource.Error(session)


        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }

    }

    override suspend fun authorizationUser(login: Login): Resource<String> {
        return try {
            val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Authorization.url){
                contentType(ContentType.Application.Json)
                setBody(login.toLoginDto())
            }
            val session = response.bodyAsText()

            if(response.status.value == 200) Resource.Success(session)
            else  Resource.Error(session)

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
            if(response.status.value == 200) {
                val userDto = Json.decodeFromString<UserDataDto>(response.bodyAsText())
                Resource.Success(userDto.toUser())
            }else Resource.Error(response.bodyAsText())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }


}