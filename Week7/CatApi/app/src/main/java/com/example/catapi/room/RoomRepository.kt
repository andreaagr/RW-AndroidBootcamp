package com.example.catapi.room

import com.example.catapi.MainActivity
import com.example.catapi.model.Cat
import com.example.catapi.model.CatRepository

class RoomRepository : CatRepository{
    private val catDao = MainActivity.catDatabase.catDao()
    override suspend fun addCat(cat: Cat) {
        catDao.insertCat(cat)
    }
    override suspend fun getCats(): List<Cat> = catDao.getAllCats()
}