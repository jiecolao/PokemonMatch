package com.example.pokemonmatch.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonmatch.db.entities.Pokemon

@Dao
interface PokemonDAO {
    @Insert
    suspend fun insertPokemon(pokemon: Pokemon)
    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)
    @Query("SELECT * FROM Cards")
    suspend fun getAllPokemon(): List<Pokemon>
}