package com.example.blockbuster.data

interface MovieRepository {
    fun addMovie(movie: Movie)
    fun getMovies() : List<Movie>
}