package com.example.pokemonmatch

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SelectDifficulty : AppCompatActivity() {

    private lateinit var btnEasy: ImageView
    private lateinit var btnMedium: ImageView
    private lateinit var btnHard: ImageView
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selectdifficulty)

        initButtons()

        btnEasy.setOnClickListener() {
            goTo(E_Game::class.java, true)
            endActivity() }
        btnMedium.setOnClickListener() {
            goTo(M_Game::class.java, true)
            endActivity() }
        btnHard.setOnClickListener() {
            goTo(H_Game::class.java, true)
            endActivity() }
        btnBack.setOnClickListener() { endActivity() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.selectdiff)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initButtons(){
        btnEasy = findViewById(R.id.imgEasy)
        btnMedium = findViewById(R.id.imgMedium)
        btnHard = findViewById(R.id.imgHard)
        btnBack = findViewById(R.id.imgBack)
    }
}