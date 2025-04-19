package com.example.pokemonmatch.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonmatch.db.dao.PokemonDAO
import com.example.pokemonmatch.db.dao.ScoreDAO
import com.example.pokemonmatch.db.dao.UserDAO
import com.example.pokemonmatch.db.entities.Pokemon
import com.example.pokemonmatch.db.entities.Score
import com.example.pokemonmatch.db.entities.User

@Database(entities = [User::class, Pokemon::class, Score::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun PokemonDAO(): PokemonDAO
    abstract fun ScoreDAO(): ScoreDAO
}