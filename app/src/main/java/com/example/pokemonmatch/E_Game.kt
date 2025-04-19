package com.example.pokemonmatch

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pokemonmatch.R.drawable.*

class E_Game : AppCompatActivity() {
    // [/] Pause feature
    // [/] Timer
    // [ ] mass produce
    // [ ] login, login as guest
    // [ ] reflect the score to db
    // [ ] card catalogue fix, update card libr, event listeners + text
    // [ ] bg music, sfx
    // [ ] compile app to exe

    /* to be applied to other game modes:
        - add streak
        - change totalMatch
        - change id referencing
        - pokemonCont take n
        - pause feature
        - play again
        - remove progress bar
    */

    lateinit var pairs: Map<ImageView, Int>
    private val flippedcards = mutableListOf<ImageView>()
    private val cardBack = R.drawable.asst_game_card
    lateinit var lblScore: TextView
    lateinit var lblStreak: TextView
    private var countScore = 0
    private var currStreak = 0
    private var matchCount = 0
    private var totalMatches = 6 // change
    private var isBusy = false
    private lateinit var gameTimer: CountDownTimer
    private var timeLeft: Long = 60000
    lateinit var lblTimer: TextView
    lateinit var pauseBtn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_egame)

        lblScore = findViewById(R.id.txtEScore)
        lblStreak = findViewById(R.id.txtEStreak)
        lblTimer = findViewById(R.id.txtTimer)
        pauseBtn = findViewById(R.id.imgPause)

        pauseBtn.setOnClickListener() { gamePaused() }

        val imgCont: Array<ImageView> = arrayOf(
            findViewById(R.id.c1), findViewById(R.id.c2), findViewById(R.id.c3),
            findViewById(R.id.c4), findViewById(R.id.c5), findViewById(R.id.c6),
            findViewById(R.id.c7), findViewById(R.id.c8), findViewById(R.id.c9),
            findViewById(R.id.c10), findViewById(R.id.c11), findViewById(R.id.c12)
        )

        val pokemonCont: MutableList<Int> = mutableListOf(
            pk_bulbasaur, pk_caterpie, pk_charmander, pk_ditto, pk_eevee,
            pk_gardevoir, pk_gengar, pk_lapras, pk_magikarp, pk_meowth,
            pk_mew, pk_pidgey, pk_pikachu, pk_piplup, pk_psyduck,
            pk_snorlax, pk_squirtle, pk_vaporeon)

        //shuffle logic
        pokemonCont.shuffle()
        val pokemons = (pokemonCont.take(6) + pokemonCont.take(6)).shuffled()
        pairs = imgCont.zip(pokemons).toMap()

        imgCont.forEach { img -> img.setOnClickListener{if (!isBusy) flipCard(img)} }

        startTimer()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.egame)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun flipCard(img: ImageView){
        if (flippedcards.contains(img)) return

        val pokemonRes = pairs[img]
        if (pokemonRes != null) {
            img.setImageResource(pokemonRes)
            flippedcards.add(img)
        }

        if (flippedcards.size == 2){
            isBusy = true
            val firstCard = flippedcards[0]
            val secondCard = flippedcards[1]

            if (pairs[firstCard] == pairs[secondCard]){ // a match
                flippedcards.clear()
                isBusy = false
                firstCard.setOnClickListener(null)
                secondCard.setOnClickListener(null)
                ++matchCount
                countScore+=5
                countScore+=currStreak
                lblScore.setText(countScore.toString())
                lblStreak.setText("+" + currStreak.toString())
                currStreak++

                if (matchCount == totalMatches){
                    gameFinished()
                }
            } else { // not a match
                Handler(mainLooper).postDelayed({
                    firstCard.setImageResource(cardBack)
                    secondCard.setImageResource(cardBack)
                    flippedcards.clear()
                    currStreak = 0
                    lblStreak.setText("+" + currStreak.toString())
                    isBusy = false
                }, 1000)
            }
        }

    }

    fun gamePaused(){
        pauseTimer()
        val dialogView = layoutInflater.inflate(R.layout.pause_game, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogView.findViewById<ImageView>(R.id.imgResume).setOnClickListener {
            dialog.dismiss()
            resumeTimer()
        }
        dialogView.findViewById<ImageView>(R.id.imgRestart).setOnClickListener {
            dialog.dismiss()
            recreate()
        }
        dialogView.findViewById<ImageView>(R.id.imgExitG).setOnClickListener {
            dialog.dismiss()
            endActivity()
        }

        dialog.show()
    }

    fun gameFinished(){
        val dialogView = layoutInflater.inflate(R.layout.finished_game, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogView.findViewById<TextView>(R.id.txtFGScore).setText(countScore.toString())
        dialogView.findViewById<ImageView>(R.id.imgPlayAgain).setOnClickListener {
            dialog.dismiss()
            recreate()
        }
        dialogView.findViewById<ImageView>(R.id.imgLeaderB).setOnClickListener {
            dialog.dismiss()
            endActivity()
            goTo(Leaderboard::class.java, true)
        }
        dialog.show()
    }

    fun startTimer(){
        gameTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val secondsLeft = millisUntilFinished / 1000
                // Update UI
                lblTimer.text = "$secondsLeft"
            }

            override fun onFinish() {
                gameFinished()
            }
        }.start()
    }

    fun pauseTimer(){
        gameTimer.cancel()
    }

    fun resumeTimer(){
        startTimer()
    }
}