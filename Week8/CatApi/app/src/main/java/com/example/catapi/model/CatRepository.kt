package com.example.catapi.model

import androidx.lifecycle.LiveData

interface CatRepository {
    suspend fun addCat(cat: Cat)
    suspend fun getCats() : LiveData<List<Cat>>
    suspend fun  getFunnyCats() : LiveData<List<FunnyCat>>
    suspend fun addFunnyCat(funnyCat: FunnyCat)
    suspend fun deleteFunnyCats()
}