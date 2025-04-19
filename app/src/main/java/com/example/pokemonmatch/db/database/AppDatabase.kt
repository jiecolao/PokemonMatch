package com.example.pokemonmatch.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonmatch.db.dao.UserDAO
import com.example.pokemonmatch.db.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}