package com.example.catapi.model

import com.example.catapi.repository.AppRepository

object Injection {
    fun providedCatRepository() : CatRepository =
        AppRepository()
}