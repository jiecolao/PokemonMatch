package com.example.pokemonmatch.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonmatch.db.entities.User

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: User)
    @Query("SELECT * FROM Users")
    suspend fun getAllUsers(): List<User>
    @Delete
    suspend fun Delete(user: User)
}