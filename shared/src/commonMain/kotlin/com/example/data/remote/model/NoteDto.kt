package com.example.data.remote.model


@kotlinx.serialization.Serializable
data class NoteDto(
    val title:String,
    val text:String,
    val id:String?,
    val color:Long = 1,
    val timestamp: Long
)