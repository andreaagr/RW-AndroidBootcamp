package com.example.catapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapi.model.Cat

@Database(entities = [(Cat::class)],version = 1)
abstract class CatDatabase : RoomDatabase(){
    abstract fun catDao(): CatDao
}