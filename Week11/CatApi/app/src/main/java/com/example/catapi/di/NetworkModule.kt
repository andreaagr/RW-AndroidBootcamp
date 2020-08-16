package com.example.catapi.di

import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.buildApiService
import org.koin.dsl.module

val networkModule = module {
    single {
        buildApiService()
    }

    single {
        RemoteApi(get())
    }
}