package com.example.blockbuster
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.blockbuster.data.Movie
import com.example.blockbuster.room.MovieDao
import com.example.blockbuster.room.MovieDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    companion object{
        lateinit var movieDatabase: MovieDatabase
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        // Creating Room Database
        movieDatabase = Room.databaseBuilder(this,MovieDatabase::class.java,"movie_database").allowMainThreadQueries().build()
        // Start the path in nav_host_fragment
        Navigation.findNavController(this, R.id.my_nav_host_fragment)
    }



}
