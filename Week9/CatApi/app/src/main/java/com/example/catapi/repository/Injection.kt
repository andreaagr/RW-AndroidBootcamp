package com.example.catapi.repository

object Injection {
    fun providedCatRepository() : CatRepository =
        AppRepository()
}