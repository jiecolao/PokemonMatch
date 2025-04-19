package com.example.pokemonmatch.db.entities

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "Users",
    indices = [Index(value = ["username"], unique = true)]
    )
data class User(
    @PrimaryKey(autoGenerate = true) val g_id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "highscore") val highscore: Long
)
