package com.example.catapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.model.Cat
import com.example.catapi.repository.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowCatsViewModel : ViewModel() {
    private val repository = Injection.providedCatRepository()
    private var cats : LiveData<List<Cat>>? = null

    init {
        // ------------Initialize list when the application loads
        viewModelScope.launch(Dispatchers.IO) {
            cats = repository.getCats()
        }
    }

    fun getCats() = cats
}