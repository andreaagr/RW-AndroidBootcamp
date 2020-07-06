package com.example.blockbuster.room

import com.example.blockbuster.MainActivity
import com.example.blockbuster.data.Movie
import com.example.blockbuster.data.MovieRepository

class RoomRepository() : MovieRepository {
    private val movieDao = MainActivity.movieDatabase.movieDao()

    override suspend fun addMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun getMovies() = movieDao.getAllMovies()
}