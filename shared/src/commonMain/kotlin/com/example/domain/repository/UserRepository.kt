package com.example.domain.repository

import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.until.Resource

interface UserRepository {
    suspend fun putUserData(user: User)
    suspend fun getUserData():User?
    suspend fun deleteUser()
    suspend fun registerUser(user: User): Resource<User>
    suspend fun observeUserData(session:String, email:String):Resource<User>
    suspend fun authUser(login: Login):Resource<String>
}