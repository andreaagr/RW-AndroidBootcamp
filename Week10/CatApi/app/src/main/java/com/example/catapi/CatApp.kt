package com.example.catapi

import android.app.Application
import com.example.catapi.di.networkModule
import com.example.catapi.di.repositoryModule
import com.example.catapi.di.viewModelModule
import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.buildApiService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CatApp : Application() {
    companion object {

        private lateinit var instance: CatApp

        /*private val apiService by lazy {
            buildApiService()
        }

        val remoteApi by lazy {
            RemoteApi(apiService)
        }*/
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //-------------------------------------------------- Start Koin
        startKoin{
            androidLogger()
            androidContext(this@CatApp)
            modules(listOf(repositoryModule,viewModelModule,networkModule))
        }
        //--------------------------------------------------------------
    }

}