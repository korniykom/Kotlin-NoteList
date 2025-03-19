package com.korniykom.notelist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note (
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val content: String
)
