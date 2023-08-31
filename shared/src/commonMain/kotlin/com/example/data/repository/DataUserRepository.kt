package com.example.data.repository

import com.example.data.database.source.UserDatabaseDataSource
import com.example.data.remote.source.UserRemoteDataSource
import com.example.domain.mapper.toUser
import com.example.domain.mapper.toUserData
import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.until.Resource

class DataUserRepository(
    private val userDatabaseDataSource: UserDatabaseDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
):UserRepository {
    override suspend fun putUserData(user: User) = userDatabaseDataSource.insertUser(user = user.toUserData())
    override suspend fun getUserData(): User? = userDatabaseDataSource.observeUser().toUser()
    override suspend fun deleteUser() = userDatabaseDataSource.deleteUser()
    override suspend fun registerUser(user: User): Resource<User> = userRemoteDataSource.registerUser(user)
    override suspend fun observeUserData(session: String, email: String): Resource<User> = userRemoteDataSource.observeUser(session = session, email = email)
    override suspend fun authUser(login: Login): Resource<String> = userRemoteDataSource.authorizationUser(login)
}