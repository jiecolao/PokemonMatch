package com.example.pokemonmatch.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.pokemonmatch.db.entities.Score
import com.example.pokemonmatch.db.entities.User

data class UsersWithScores( // data container, is not a table, just for fetching
    val g_id: Int,
    val username: String,
    val highscore: Long
)

@Dao
interface ScoreDAO {
    @Upsert
    suspend fun insertScore(score: Score)
    @Delete
    suspend fun deleteScore(score: Score)
    @Query("""
        SELECT Users.g_id,
            Users.username,
            Score.highscore
        FROM Score
        INNER JOIN Users on Score.g_id = Users.g_id
        WHERE Users.g_id = :g_id
    """)
    suspend fun getUserScore(g_id: Int): List<UsersWithScores>
    @Query("SELECT * FROM Score")
    suspend fun getAllScores(): List<Score>
}