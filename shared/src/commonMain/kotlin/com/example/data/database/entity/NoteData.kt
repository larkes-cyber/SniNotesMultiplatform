package com.example.data.database.entity

class NoteData(
    var id:String,
    val title:String,
    val text:String,
    val color:Long,
    val online_sync:Boolean,
    val visible:Boolean,
    val timestamp: Long
)