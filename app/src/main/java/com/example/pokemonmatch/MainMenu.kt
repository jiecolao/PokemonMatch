package com.example.pokemonmatch

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMenu : AppCompatActivity() {

    private lateinit var btnPlay: Button
    private lateinit var btnOptions: Button
    private lateinit var btnLeaderboard: Button
    private lateinit var btnCatalogue: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainmenu)

        initButtons() // init buttons

        btnPlay.setOnClickListener() { goTo(SelectDifficulty::class.java, true) }
        btnOptions.setOnClickListener() { goTo(Options::class.java, true) }
        btnLeaderboard.setOnClickListener() { goTo(Ranking::class.java, true) }
        btnCatalogue.setOnClickListener() { goTo(CardCatalogue::class.java, true) }
        btnExit.setOnClickListener() { endApp() }

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
}