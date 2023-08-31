package com.example.domain.mapper

import com.example.data.database.entity.UserData
import com.example.data.remote.model.UserDataDto
import com.example.data.remote.model.UserDto
import com.example.domain.model.User

fun User.toUserDto():UserDto{
    return UserDto(
        userName = name,
        email = login,
        password = password
    )
}


fun UserDataDto.toUser():User{
    return User(
        name = name,
        password = password,
        login = login,
        session = session
    )
}

fun UserData.toUser():User{
    return User(
        name = name,
        password = password,
        login = login,
        session = session
    )
}

fun User.toUserData():UserData{
    return UserData(
        name = name,
        password = password,
        login = login,
        session = session
    )
}