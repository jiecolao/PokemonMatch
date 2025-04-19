package com.example.pokemonmatch.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cards")
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val pk_id: Int,
    @ColumnInfo(name = "pk_name") val pk_name: String,
    @ColumnInfo(name = "pk_desc") val pk_desc: String
)
