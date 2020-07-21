package com.example.catapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catapi.model.Cat

@Dao
interface CatDao {
    @Insert
    suspend fun insertCat(cat: Cat)

    @Query("SELECT * FROM cat_table")
    suspend fun getAllCats() : List<Cat>
}