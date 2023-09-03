package com.example.data.remote.model

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class UserDto(
    val email:String,
    var password:String,
    val userName:String = ""
)