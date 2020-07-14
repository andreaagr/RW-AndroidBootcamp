package com.example.blockbuster.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.blockbuster.data.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies() : List<Movie>
}
