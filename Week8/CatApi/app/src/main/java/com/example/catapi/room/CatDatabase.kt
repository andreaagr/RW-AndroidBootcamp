package com.example.catapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapi.model.Cat
import com.example.catapi.model.FunnyCat

@Database(entities = [(Cat::class),(FunnyCat::class)],version = 1)
abstract class CatDatabase : RoomDatabase(){
    abstract fun catDao(): CatDao
}