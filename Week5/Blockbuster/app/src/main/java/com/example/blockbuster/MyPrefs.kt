package com.example.blockbuster

import android.content.Context
import android.content.SharedPreferences


class MyPrefs(var context: Context) {
    //companion object{
        //var userLogged : Boolean = false
    //}

    fun savePrefs(userLogged : Boolean){
        val prefs: SharedPreferences =context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("isUserLogged",userLogged)
        editor.apply()
    }


    fun getPrefs() : Boolean{
        val prefs: SharedPreferences =context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("isUserLogged", false)
    }

}