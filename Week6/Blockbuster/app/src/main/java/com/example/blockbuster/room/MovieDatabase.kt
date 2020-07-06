package com.example.blockbuster.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blockbuster.data.Movie

@Database(entities = [(Movie::class)],version = 1)
@TypeConverters(ImageConverter::class)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}