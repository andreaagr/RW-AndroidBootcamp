package com.example.catapi.repository

import androidx.lifecycle.LiveData
import com.example.catapi.model.Breed
import com.example.catapi.model.Cat
import com.example.catapi.model.FunnyCat

interface CatRepository {
    suspend fun addCat(cat: Cat)
    suspend fun getCats() : LiveData<List<Cat>>
    suspend fun  getFunnyCats() : LiveData<List<FunnyCat>>
    suspend fun addFunnyCat(funnyCat: FunnyCat)
    suspend fun deleteFunnyCats()
    suspend fun searchCat(breedId : String) : Cat?
    suspend fun getBreedList() : List<Breed>?
}