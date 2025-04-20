package com.example.pokemonmatch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.pokemonmatch.db.database.AppDatabase
import com.example.pokemonmatch.db.entities.User
import kotlinx.coroutines.launch
import kotlin.math.log

class Login : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var guestBtn: Button
    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var db: AppDatabase
    private var isNew = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // db handler
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "pokemonmatch-db"
        ).build()
        val userDAO = db.userDao()

        loginBtn = findViewById(R.id.btnLogin)
        guestBtn = findViewById(R.id.btnGuest)
        txtUsername = findViewById(R.id.inpUsername)
        txtPassword = findViewById(R.id.inpPass)

        loginBtn.setOnClickListener(){
            val username = txtUsername.text.toString().lowercase().trim()
            val userpass = txtPassword.text.toString().lowercase().trim()
            val newUser = User(username = username, password = userpass)
            try {
                lifecycleScope.launch {
                    if (userDAO.isUserNew(username)) {
                        AlertDialog.Builder(this@Login)
                            .setTitle("No Existing User Found")
                            .setMessage("Would you like to sign up with the details you provided?")
                            .setPositiveButton("Sign Up") { dialog, _ ->
                                lifecycleScope.launch {
                                    userDAO.insertUser(newUser)
                                    goTo(MainMenu::class.java, true)
                                    endActivity()
                                    var result = userDAO.getAllUsers()
                                    Log.d("UserCredentials", "Result: $result")
                                    setUserSession(userDAO.getUserGId(username).g_id)
                                    Log.d("UserCredentials", "Current ID in Session: ${SessionUtil.g_id}")
                                }
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    } else if (userpass == userDAO.verifyUser(username).password){
                        // pass in g_id to SessionManager
                        goTo(MainMenu::class.java, true)
                        endActivity()
                        Toast.makeText(this@Login, "Welcome, $username!", Toast.LENGTH_SHORT).show()
                        setUserSession(userDAO.getUserGId(username).g_id)
                        Log.d("UserCredentials", "Current ID in Session: ${SessionUtil.g_id}")
                    } else {
                        AlertDialog.Builder(this@Login)
                            .setTitle("Login Error")
                            .setMessage("Incorrect password. Please try again.")
                            .setPositiveButton("OK", null)
                            .show()
                    }
                }
            } catch (e: Exception){
                Log.e("LoginAttemptFailed", "Error: $e")
            }
        }
        guestBtn.setOnClickListener(){
            AlertDialog.Builder(this@Login)
                .setTitle("Play as Guest")
                .setMessage("Would you like to play as Guest?")
                .setPositiveButton("Yes") { dialog, _ ->
                    lifecycleScope.launch {
                        var guestName = generateGuestName() // ** catch for existing users
                        do {
                            if (userDAO.isUserNew(guestName)) {
                                // pass in g_id to SessionManager
                                val newUser = User(username = guestName, password = "0")
                                userDAO.insertUser(newUser)

                                goTo(MainMenu::class.java, true)
                                endActivity()
                                Toast.makeText(this@Login, "Welcome, $guestName!", Toast.LENGTH_SHORT).show()
                                setUserSession(userDAO.getUserGId(guestName).g_id)
                                var result = userDAO.getAllUsers()
                                Log.d("UserCredentials", "Result: $result")
                                Log.d("UserCredentials", "Current ID in Session: ${SessionUtil.g_id}")
                            } else {
                                guestName = generateGuestName().trim()
                            }
                        } while(!userDAO.isUserNew(guestName))
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun generateGuestName(): String {
        val randomNumber = (0..999).random() // Generates number from 0 to 999
        return "guest" + String.format("%03d", randomNumber) // Pads number to 3 digits
    }

    fun setUserSession(gId: Int){
        SessionUtil.g_id = gId
//        SessionUtil.setUsername()
    }
}