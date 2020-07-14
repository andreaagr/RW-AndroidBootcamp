package com.example.blockbuster.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(val releaseDate: String,
                 var title: String,
                 val summary: String?,
                 var poster: Any?, var genre: String, var stars: Float){

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}