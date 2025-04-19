package com.example.pokemonmatch.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "Score",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["g_id"],
            childColumns = ["g_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["g_id"])]
)
data class Score(
    @PrimaryKey(autoGenerate = true) val s_id: Int,
    val g_id: String,
    @ColumnInfo(name = "highscore") val highscore: Long
)
