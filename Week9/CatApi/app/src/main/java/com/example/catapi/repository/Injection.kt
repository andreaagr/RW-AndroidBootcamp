package com.example.catapi.repository

import com.example.catapi.repository.AppRepository
import com.example.catapi.repository.CatRepository

object Injection {
    fun providedCatRepository() : CatRepository =
        AppRepository()
}