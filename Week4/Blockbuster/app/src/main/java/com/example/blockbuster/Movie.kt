package com.example.blockbuster

import java.io.Serializable
import java.util.*

class Movie(
    val id: Int, val releaseDate: Date?,
    var title: String,
    val summary: String,
    var poster: Any?, var genre: String, var stars: Float) : Serializable