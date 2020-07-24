package com.example.catapi.room

import androidx.lifecycle.LiveData
import com.example.catapi.MainActivity
import com.example.catapi.model.Cat
import com.example.catapi.model.CatRepository
import com.example.catapi.model.FunnyCat

class RoomRepository : CatRepository{
    private val catDao = MainActivity.catDatabase.catDao()
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
}