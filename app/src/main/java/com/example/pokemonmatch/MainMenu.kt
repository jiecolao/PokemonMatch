package com.example.pokemonmatch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.pokemonmatch.db.dao.UserDAO
import com.example.pokemonmatch.db.database.AppDatabase
import com.example.pokemonmatch.db.entities.User
import kotlinx.coroutines.launch


class MainMenu : AppCompatActivity() {

    private lateinit var btnPlay: Button
    private lateinit var btnOptions: Button
    private lateinit var btnLeaderboard: Button
    private lateinit var btnCatalogue: Button
    private lateinit var btnExit: Button

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainmenu)

        initButtons() // init buttons
        btnPlay.setOnClickListener() { goTo(SelectDifficulty::class.java, true) }
        btnOptions.setOnClickListener() { goTo(Options::class.java, true) }
        btnLeaderboard.setOnClickListener() { goTo(Leaderboard::class.java, true) }
        btnCatalogue.setOnClickListener() { goTo(CardCatalogue::class.java, true) }
        btnExit.setOnClickListener() { endApp() }

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "pokemonmatch-db"
        ).build()

        val userDAO = db.userDao()
        val newUser = User(g_id = 2, username = "Ash", password = "123", highscore = 0)

        lifecycleScope.launch {
//            userDAO.insertUser(newUser)
            var users = userDAO.getAllUsers()
            Log.d("AllUsesQuery", "Fetched: $users" )
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initButtons(){
        btnPlay = findViewById(R.id.btnPlay)
        btnOptions = findViewById(R.id.btnOptions)
        btnLeaderboard = findViewById(R.id.btnLB)
        btnCatalogue = findViewById(R.id.btnCat)
        btnExit = findViewById(R.id.btnExit)
    }

    private fun initdraft(){
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "pokemonmatch-db"
        ).build()

        val userDAO = db.userDao()
//        val pokemonDAO = db.PokemonDAO()
        val newUser = User(g_id = 2, username = "Ash", password = "123", highscore = 0)

        lifecycleScope.launch {
//            userDAO.insertUser(newUser)
            var users = userDAO.getAllUsers()
//            var pokemons = pokemonDAO.getAllPokemon()
//            Log.d("AllPokemonsQuery", "Fetched: $pokemons")
        }
    }
}