package com.example.catapi.networking

import com.example.catapi.model.Breed
import com.example.catapi.model.Cat
import com.example.catapi.model.FunnyCat
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("/v1/breeds")
    suspend fun obtainBreedList(): List<Breed>

    @GET("/v1/images/search")
    suspend fun obtainCatPhoto(@Query("breed_ids") breedId: String) : List<Cat>

    @GET("/v1/images/search")
    suspend fun getRandomFunnyImage(@Query ("category_ids") categoryId : String) : List<FunnyCat>
}