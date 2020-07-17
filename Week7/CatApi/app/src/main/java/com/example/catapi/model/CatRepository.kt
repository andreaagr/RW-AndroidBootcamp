package com.example.catapi.model

interface CatRepository {
    suspend fun addCat(cat: Cat)
    suspend fun getCats() : List<Cat>
}