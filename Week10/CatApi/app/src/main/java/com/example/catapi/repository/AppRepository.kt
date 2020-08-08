package com.example.catapi.repository

import androidx.lifecycle.LiveData
import com.example.catapi.model.Breed
import com.example.catapi.model.Cat
import com.example.catapi.model.FunnyCat
import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.Success
import com.example.catapi.room.CatDao
import org.koin.core.KoinComponent


class AppRepository(private val remoteApi: RemoteApi, private val catDao: CatDao) : CatRepository,KoinComponent {
    override suspend fun addCat(cat: Cat) {
        catDao.insertCat(cat)
    }
    override suspend fun getCats(): LiveData<List<Cat>> = catDao.getAllCats()

    override suspend fun getFunnyCats(): LiveData<List<FunnyCat>> = catDao.getFunnyCats()

    override suspend fun addFunnyCat(funnyCat: FunnyCat) {
        catDao.addFunnyCat(funnyCat)
    }

    override suspend fun deleteFunnyCats() {
        catDao.deleteFunnyCats()
    }

    override suspend fun searchCat(breedId : String): Cat? {
        val result = remoteApi.getCatImage(breedId)
        if(result is Success){
            if(result.data.isNotEmpty())
                return result.data[0]
        }
        return null
    }

    override suspend fun getBreedList(): List<Breed>? {
        val  result = remoteApi.getBreedList()
        if (result is Success){
            if(result.data.isNotEmpty())
                return result.data
        }
        return null
    }
}


