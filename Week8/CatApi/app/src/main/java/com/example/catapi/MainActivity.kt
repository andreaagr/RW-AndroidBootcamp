package com.example.catapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.catapi.model.MyViewModel
import com.example.catapi.room.CatDatabase

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var catDatabase: CatDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        // Creating Room Database
        catDatabase = Room.databaseBuilder(this,CatDatabase::class.java,"cat_database").build()
        // Initializing ViewModel
        ViewModelProvider(this).get(MyViewModel::class.java)
    }


}