package com.example.data.database.entity

import database.UserEntity

fun UserEntity.toUserData():UserData{
    return UserData(
        session = session,
        password = password,
        login = login,
        name = name
    )
}