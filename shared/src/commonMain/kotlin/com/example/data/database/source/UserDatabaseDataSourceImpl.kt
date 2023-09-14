package com.example.data.database.source

import com.example.data.database.entity.UserData
import com.example.data.database.entity.toUserData
import com.example.kmpsninotes.database.NoteDatabase

class UserDatabaseDataSourceImpl(
    private val db: NoteDatabase
):UserDatabaseDataSource {

    private val queries = db.userQueries


    override suspend fun insertUser(user: UserData) {
        queries.insertUser(
            session = user.session,
            password = user.password,
            login = user.login,
            name = user.name
        )
    }

    override suspend fun observeUser(): UserData? {
        return try {
            queries.observeUser().executeAsOne().toUserData()
        } catch (e:Exception){
            null
        }
    }

    override suspend fun deleteUser() {
        queries.deleteUser()
    }
}