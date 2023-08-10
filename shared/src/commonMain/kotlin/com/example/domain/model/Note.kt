package com.example.domain.model

data class Note(
    var id:String,
    val title:String,
    val text:String,
    val color:Long,
    val online_sync:Boolean,
    val visible:Boolean,
    val timestamp: Long
)