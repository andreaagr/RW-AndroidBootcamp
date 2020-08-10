package com.example.catapi.networking

import com.example.catapi.model.*

class RemoteApi (private val apiService: RemoteApiService){

    suspend fun getBreedList() : Result<List<Breed>> = try {
        val data = apiService.obtainBreedList()
        Success(data)
    }catch (error : Throwable){
        Failure(error)
    }

    suspend fun getCatImage(breedId : String) : Result<List<Cat>> = try {
        val data = apiService.obtainCatPhoto(breedId)
        Success(data)
    }catch (error : Throwable){
        Failure(error)
    }

    suspend fun getFunnyPhoto(categoryId : String) : Result<List<FunnyCat>> = try {
        val data = apiService.getRandomFunnyImage(categoryId)
        Success(data)
    }catch (error : Throwable){
        Failure(error)
    }
}