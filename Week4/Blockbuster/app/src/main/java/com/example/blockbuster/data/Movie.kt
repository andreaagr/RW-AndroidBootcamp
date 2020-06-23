package com.example.blockbuster.data

import java.io.Serializable

class Movie(
    val id: Int, val releaseDate: String,
    var title: String,
    val summary: String?,
    var poster: Any?, var genre: String, var stars: Float) : Serializable