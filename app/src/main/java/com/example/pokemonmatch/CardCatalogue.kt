package com.example.pokemonmatch

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardCatalogue : AppCompatActivity() {

    private lateinit var btnBack: ImageView

    private lateinit var btnCharmander: ImageView
    private lateinit var btnBulbasaur: ImageView
    private lateinit var btnSquirtle: ImageView
    private lateinit var btnCaterpie: ImageView
    private lateinit var btnDitto: ImageView
    private lateinit var btnEevee: ImageView
    private lateinit var btnGardevoir: ImageView
    private lateinit var btnGengar: ImageView
    private lateinit var btnLapras: ImageView
    private lateinit var btnPikachu: ImageView
    private lateinit var btnMew: ImageView
    private lateinit var btnMeowth: ImageView
    private lateinit var btnPiplup: ImageView
    private lateinit var btnPsyduck: ImageView
    private lateinit var btnSnorlax: ImageView
    private lateinit var btnVaporeon: ImageView
    private lateinit var btnMagikarp: ImageView
    private lateinit var btnPidgey: ImageView

    private lateinit var imgPokemon: ImageView
    private lateinit var pkDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cardcatalogue)

        imgPokemon = findViewById(R.id.pokemon_view)
        pkDesc = findViewById(R.id.pk_desc)
        btnBack = findViewById(R.id.imgMM)
        btnBack.setOnClickListener() { endActivity() }

        btnCharmander = findViewById(R.id.pk_charmander)
        btnBulbasaur = findViewById(R.id.pk_bulbasaur)
        btnSquirtle = findViewById(R.id.pk_squirtle)
        btnCaterpie = findViewById(R.id.pk_caterpie)
        btnDitto = findViewById(R.id.pk_ditto)
        btnEevee = findViewById(R.id.pk_eevee)
        btnGardevoir = findViewById(R.id.pk_gardevoir)
        btnGengar = findViewById(R.id.pk_gengar)
        btnLapras = findViewById(R.id.pk_lapras)
        btnPikachu = findViewById(R.id.pk_pikachu)
        btnMew = findViewById(R.id.pk_mew)
        btnMeowth = findViewById(R.id.pk_meowth)
        btnPiplup = findViewById(R.id.pk_piplup)
        btnPsyduck = findViewById(R.id.pk_psyduck)
        btnSnorlax = findViewById(R.id.pk_snorlax)
        btnVaporeon = findViewById(R.id.pk_vaporeon)
        btnMagikarp = findViewById(R.id.pk_magikarp)
        btnPidgey = findViewById(R.id.pk_pidgey)

        btnCharmander.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_charmander)
            pkDesc.setText(R.string.charmander)
        }

        btnBulbasaur.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_bulbasaur)
            pkDesc.setText(R.string.bulbasaur)
        }

        btnSquirtle.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_squirtle)
            pkDesc.setText(R.string.squirtle)
        }

        btnCaterpie.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_caterpie)
            pkDesc.setText(R.string.caterpie)
        }

        btnDitto.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_ditto)
            pkDesc.setText(R.string.ditto)
        }

        btnEevee.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_eevee)
            pkDesc.setText(R.string.eevee)
        }

        btnGardevoir.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_gardevoir)
            pkDesc.setText(R.string.gardevoir)
        }

        btnGengar.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_gengar)
            pkDesc.setText(R.string.gengar)
        }

        btnLapras.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_lapras)
            pkDesc.setText(R.string.lapras)
        }

        btnPikachu.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_pikachu)
            pkDesc.setText(R.string.pikachu)
        }

        btnMew.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_mew)
            pkDesc.setText(R.string.mew)
        }

        btnMeowth.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_meowth)
            pkDesc.setText(R.string.meowth)
        }

        btnPiplup.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_piplup)
            pkDesc.setText(R.string.piplup)
        }

        btnPsyduck.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_psyduck)
            pkDesc.setText(R.string.psyduck)
        }

        btnSnorlax.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_snorlax)
            pkDesc.setText(R.string.snorlax)
        }

        btnVaporeon.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_vaporeon)
            pkDesc.setText(R.string.vaporeon)
        }

        btnMagikarp.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_magikarp)
            pkDesc.setText(R.string.magikarp)
        }

        btnPidgey.setOnClickListener(){
            imgPokemon.setImageResource(R.drawable.pk_pidgey)
            pkDesc.setText(R.string.pidgey)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cardcat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}