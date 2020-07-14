package com.example.blockbuster.room

import android.os.AsyncTask
import com.example.blockbuster.MainActivity
import com.example.blockbuster.data.Movie
import com.example.blockbuster.data.MovieRepository

class RoomRepository() : MovieRepository {
    private val movieDao = MainActivity.movieDatabase.movieDao()
    private var allMovies : List<Movie>

    init { allMovies = movieDao.getAllMovies() }

    override fun addMovie(movie: Movie){
        InsertMovieAsyncTask(movieDao).execute(movie)
    }

    override fun getMovies() = allMovies

    private class InsertMovieAsyncTask internal constructor(private val movieDao: MovieDao) : AsyncTask<Movie, Void, Void>(){
        override fun doInBackground(vararg p0: Movie?): Void ?{
            movieDao.insertMovie(p0[0]!!)
            return null
        }

    }

}