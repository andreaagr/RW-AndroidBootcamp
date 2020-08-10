package com.example.catapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.model.FunnyCat
import com.example.catapi.repository.AppRepository
import kotlinx.coroutines.launch

class FunnyCatsViewModel(private val repository: AppRepository) : ViewModel(){
    private var funnyCatList : LiveData<List<FunnyCat>>? = null

    init {
        viewModelScope.launch{
            funnyCatList = repository.getFunnyCats()
        }
    }

    fun getFunnyCats() = funnyCatList
}