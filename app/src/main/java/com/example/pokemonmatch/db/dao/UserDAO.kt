package com.example.pokemonmatch.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonmatch.db.entities.User

@Dao
interface UserDAO {
    // uspsert, delete score
    // erase data nanaman
    @Insert
    suspend fun insertUser(user: User)
    @Delete
    suspend fun deleteUser(user: User) // try change to only delete by g_id
    @Query("SELECT * FROM Users WHERE g_id = :g_id")
    suspend fun getUser(g_id: Int): List<User>
    @Query("SELECT * FROM Users")
    suspend fun getAllUsers(): List<User>
}