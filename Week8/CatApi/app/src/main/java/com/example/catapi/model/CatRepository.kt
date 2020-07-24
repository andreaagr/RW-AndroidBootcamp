package com.example.catapi.model

import androidx.lifecycle.LiveData

interface CatRepository {
    suspend fun addCat(cat: Cat)
    suspend fun getCats() : LiveData<List<Cat>>
}