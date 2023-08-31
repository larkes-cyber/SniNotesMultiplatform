package com.example.data.remote.model

@kotlinx.serialization.Serializable
class UserDataDto(
    val login:String,
    val password:String,
    val session:String,
    val name:String
)