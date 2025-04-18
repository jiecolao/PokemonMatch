package com.example.pokemonmatch

import android.app.Activity
import android.content.Intent

fun Activity.goTo(activityClass: Class<out Activity>, finishCurrent: Boolean = false){
    Intent(this, activityClass).also { startActivity(it) }
}

fun Activity.endActivity(){
    finish()
}

fun Activity.endApp(){
    finishAffinity()
}