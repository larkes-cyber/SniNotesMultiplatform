package com.example.data.remote.model

@kotlinx.serialization.Serializable
data class LoginDto(
    val email:String,
    val password:String
)