package com.example.catapi

import android.app.Application
import com.example.catapi.model.Breed
import com.example.catapi.model.Cat
import com.example.catapi.model.FunnyCat
import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.buildApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class App : Application() {
    companion object {

        private lateinit var instance: App

        // ------------------------------------ For Api with List<Breed> adapter
        private val apiServiceBreed by lazy {
            val type: Type = Types.newParameterizedType(List::class.java, Breed::class.java)
            val parserM = Moshi.Builder().build()
            val reportAdapter: JsonAdapter<List<Breed>> = parserM.adapter(type)
            buildApiService(parserM)
        }

        val remoteApiBreed by lazy {
            RemoteApi(apiServiceBreed)
        }
        //----------------------------------------------------------------------
        // -------------------------------------- For Api with List<Cat> adapter
        private val apiServiceImage by lazy {

            val type: Type = Types.newParameterizedType(List::class.java, Cat::class.java)
            val parserM = Moshi.Builder().build()
            val reportAdapter: JsonAdapter<List<Cat>> = parserM.adapter(type)
            buildApiService(parserM)

        }

        val remoteApiImage by lazy {
            RemoteApi(apiServiceImage)
        }
        //-------------------------------------------------------------------------
        // ------------------------------------ For Api with List<FunnyCat> adapter
        private val apiServiceCat by lazy {
            val type: Type = Types.newParameterizedType(List::class.java, FunnyCat::class.java)
            val parserM = Moshi.Builder().build()
            val reportAdapter: JsonAdapter<List<FunnyCat>> = parserM.adapter(type)
            buildApiService(parserM)
        }

        val remoteApiCat by lazy {
            RemoteApi(apiServiceCat)
        }
        //------------------------------------------------------------------------
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}