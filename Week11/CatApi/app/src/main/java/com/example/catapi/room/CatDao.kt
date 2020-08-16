package com.example.catapi.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catapi.model.Cat
import com.example.catapi.model.FunnyCat

@Dao
interface CatDao {
    @Insert
    suspend fun insertCat(cat: Cat)

    @Query("SELECT * FROM cat_table")
    fun getAllCats() : LiveData<List<Cat>>

    @Query("SELECT * FROM funny_cat_table")
    fun getFunnyCats() : LiveData<List<FunnyCat>>

    @Insert
    suspend fun addFunnyCat(funnyCat: FunnyCat)

    @Query("DELETE FROM funny_cat_table")
    suspend fun deleteFunnyCats()
}