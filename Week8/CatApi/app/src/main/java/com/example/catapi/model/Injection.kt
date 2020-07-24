package com.example.catapi.model

import com.example.catapi.room.RoomRepository

object Injection {
    fun providedCatRepository() : CatRepository = RoomRepository()
}