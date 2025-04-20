package com.example.pokemonmatch.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pokemonmatch.db.entities.User

data class ExistingUser(
    val username: String
)

data class VerifyUserResult(
    val username: String,
    val password: String
)

data class UserGID(
    val g_id: Int,
    val username: String
)

data class ScoreRanking(
    val username: String,
    val highscore: Long
)

@Dao
interface UserDAO {
    // uspsert, delete score
    // erase data nanaman
    // isang table nalang
    @Insert
    suspend fun insertUser(user: User)
    @Delete
    suspend fun deleteUser(user: User) // try change to only delete by g_id
    @Query("UPDATE Users SET highscore = :score WHERE g_id = :g_id")
    suspend fun updateScore(g_id: Int?, score: Long?)
    @Query("SELECT NOT EXISTS(SELECT 1 FROM Users WHERE username = :username)")
    suspend fun isUserNew(username: String): Boolean
    @Query("SELECT username, password FROM Users WHERE username = :username")
    suspend fun verifyUser(username: String): VerifyUserResult
    @Query("SELECT g_id, username FROM Users WHERE username = :username")
    suspend fun getUserGId(username: String): UserGID
    @Query("SELECT * FROM Users")
    suspend fun getAllUsers(): List<User>
    @Query("SELECT username, highscore FROM Users WHERE g_id = :g_id")
    suspend fun getScore(g_id: Int?): ScoreRanking
    @Query("SELECT username, highscore FROM users WHERE highscore IS NOT NULL ORDER BY highscore DESC")
    suspend fun getRanking(): List<ScoreRanking>
}