package com.example.catapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.model.Cat
import com.example.catapi.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ShowCatsViewModel : ViewModel(), KoinComponent{
    private val repository : AppRepository by inject()
    private var cats : LiveData<List<Cat>>? = null

    init {
        // ------------Initialize list when the application loads
        viewModelScope.launch{
            cats = repository.getCats()
        }
    }

    fun getCats() = cats
}