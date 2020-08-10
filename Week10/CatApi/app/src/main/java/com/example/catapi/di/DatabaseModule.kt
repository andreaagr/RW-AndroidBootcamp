package com.example.catapi.di

import androidx.room.Room
import com.example.catapi.room.CatDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), CatDatabase::class.java, "cat_database.db").build()
    }
    single { get<CatDatabase>().catDao() }
}