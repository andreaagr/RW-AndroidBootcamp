package com.example.catapi.model

import com.example.catapi.room.RoomRepository

object Injection {
    fun providedMovieRepository() : CatRepository = RoomRepository()
}