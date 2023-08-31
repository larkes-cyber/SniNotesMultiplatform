package com.example.data.remote.source

import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.until.Constants.SNI_NOTES_URL
import com.example.until.Resource

interface UserRemoteDataSource {
    suspend fun registerUser(user: User):Resource<User>
    suspend fun authorizationUser(login: Login):Resource<String>
    suspend fun observeUser(session:String, email:String):Resource<User>
    companion object{
        const val BASE_URL = "${SNI_NOTES_URL}/user"
    }

    sealed class Endpoints(val url: String){
        object Register: Endpoints("$BASE_URL/register")
        object Authorization: Endpoints("$BASE_URL/authorization")
    }
}