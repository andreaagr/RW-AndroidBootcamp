package com.example.blockbuster.data

interface MovieRepository {
    suspend fun addMovie(movie: Movie)
    suspend fun getMovies() : List<Movie>
}