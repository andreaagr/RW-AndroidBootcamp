package com.example.catapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.model.FunnyCat
import com.example.catapi.repository.Injection
import kotlinx.coroutines.launch

class FunnyCatsViewModel : ViewModel() {
    private val repository = Injection.providedCatRepository()
    private var funnyCatList : LiveData<List<FunnyCat>>? = null

    init {
        viewModelScope.launch{
            funnyCatList = repository.getFunnyCats()
        }
    }

    fun getFunnyCats() = funnyCatList
}