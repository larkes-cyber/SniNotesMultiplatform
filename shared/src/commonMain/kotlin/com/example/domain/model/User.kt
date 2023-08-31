package com.example.domain.model

data class User(
    val login:String,
    val password:String,
    val session:String = "",
    val name:String
)