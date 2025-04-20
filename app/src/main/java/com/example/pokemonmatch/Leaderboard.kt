package com.example.pokemonmatch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.pokemonmatch.db.database.AppDatabase
import kotlinx.coroutines.launch

class Leaderboard : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var db: AppDatabase
    private lateinit var usernameCont: List<TextView>
    private lateinit var scoreCont: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_leaderboard)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "pokemonmatch-db"
        ).build()
        val userDAO = db.userDao()

        usernameCont = listOf(
            findViewById(R.id.rank1),
            findViewById(R.id.rank2),
            findViewById(R.id.rank3),
            findViewById(R.id.rank4),
            findViewById(R.id.rank5))
        scoreCont = listOf(
            findViewById(R.id.score1),
            findViewById(R.id.score2),
            findViewById(R.id.score3),
            findViewById(R.id.score4),
            findViewById(R.id.score5))
        loadLeaderboard()


        btnBack = findViewById(R.id.imgExitLB)

        btnBack.setOnClickListener() { endActivity() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ranking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadLeaderboard() {
        val userDAO = db.userDao()
        lifecycleScope.launch {
            val rankings = userDAO.getRanking()
            for (i in 0 until 5) {
                if (i < rankings.size) {
                    usernameCont[i].text = rankings[i].username
                    scoreCont[i].text = rankings[i].highscore.toString()
                } else {
                    usernameCont[i].text = "-"
                    scoreCont[i].text = "-"
                }
            }
        }
    }
}