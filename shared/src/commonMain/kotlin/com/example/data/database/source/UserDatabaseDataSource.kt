package com.example.data.database.source

import com.example.data.database.entity.UserData


interface UserDatabaseDataSource {

    suspend fun insertUser(user: UserData)
    suspend fun observeUser():UserData?
    suspend fun deleteUser()

}