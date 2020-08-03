package com.example.catapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.model.Cat
import com.example.catapi.repository.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCatViewModel : ViewModel() {
    private val repository = Injection.providedCatRepository()
    private val _viewState = MutableLiveData<UIResponseState>().apply {
        value = UIResponseState.Waiting
    }

    val viewState: LiveData<UIResponseState> = _viewState

    fun addCat(cat: Cat) {
        // Adding the element into database
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCat(cat)
        }
    }

    fun searchCat(breedId: String) {
        _viewState.value = UIResponseState.Loading
        viewModelScope.launch {
            val cat = repository.searchCat(breedId)
            cat?.let {
                _viewState.postValue(UIResponseState.Success(cat))
            } ?: _viewState.postValue(UIResponseState.Error("Error"))
        }
    }

    fun obtainBreedList() {
        _viewState.value = UIResponseState.Loading
        viewModelScope.launch {
            val breedMap = repository.getBreedList()
            breedMap?.let {
                _viewState.postValue(UIResponseState.Success(breedMap.map { it.name to it.id }.toMap()))
            } ?: _viewState.postValue(UIResponseState.Error("Check your connection"))
        }
    }

    fun emptyName(cat : Cat) : Boolean{
        return cat.name.isEmpty()
    }
}