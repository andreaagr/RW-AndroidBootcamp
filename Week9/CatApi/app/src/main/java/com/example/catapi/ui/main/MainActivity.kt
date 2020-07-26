package com.example.catapi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.catapi.R
import com.example.catapi.room.CatDatabase
import com.example.catapi.viewmodel.FunnyCatsViewModel
import com.example.catapi.viewmodel.ShowCatsViewModel

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
        // Initializing ViewModels
        ViewModelProvider(this).get(ShowCatsViewModel::class.java)
        ViewModelProvider(this).get(FunnyCatsViewModel::class.java)
    }
}