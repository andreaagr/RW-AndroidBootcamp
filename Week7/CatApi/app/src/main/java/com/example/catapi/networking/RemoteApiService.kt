package com.example.catapi.networking

import com.example.catapi.model.Breed
import com.example.catapi.model.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("/v1/breeds")
    suspend fun obtainBreedList(): List<Breed>


    @GET("/v1/images/search")
    //fun obtainCatPhoto(): Cat
    suspend fun obtainCatPhoto(@Query("breed_ids") breedId: String) : List<Cat>
}