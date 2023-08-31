package com.example.domain.mapper

import com.example.data.remote.model.LoginDto
import com.example.domain.model.Login

fun Login.toLoginDto(): LoginDto = LoginDto(
    password = password,
    email = login
)