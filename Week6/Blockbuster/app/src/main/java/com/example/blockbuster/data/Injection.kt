package com.example.blockbuster.data

import com.example.blockbuster.room.RoomRepository

object Injection {
    fun providedMovieRepository() : MovieRepository = RoomRepository()
}