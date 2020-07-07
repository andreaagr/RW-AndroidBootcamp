package com.example.blockbuster.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.blockbuster.data.Movie

@Dao
interface MovieDao {
    @Insert
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : List<Movie>
}
