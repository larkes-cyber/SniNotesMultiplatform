package com.example.domain.model

data class Note(
    var id:String,
    val title:String,
    val text:String,
    val color:Long,
    var online_sync:Boolean,
    val visible:Boolean = true,
    val timestamp: Long
){
    companion object {
        private val colors = listOf(0xFFD0BCFF, 0xFFCCC2DC, 0xFFEFB8C8, 0xFFF2B8B5, 0xFFCAC5CD)
        fun generateRandom():Long{
            return colors.random()
        }
    }
}