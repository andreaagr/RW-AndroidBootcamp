package com.example.catapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.model.Cat
import com.example.catapi.repository.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCatViewModel : ViewModel() {
    private val repository = Injection.providedCatRepository()
    private var cat = MutableLiveData<Cat>()
    private val breedMap = MutableLiveData<Map<String,String>>()

    fun addCat(cat: Cat){
        // Adding the element into database
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCat(cat)
        }
    }

    fun searchCat(breedId : String) {
        viewModelScope.launch {
            cat.postValue(repository.searchCat(breedId))
        }
    }

    fun obtainBreedList(){
        viewModelScope.launch {
            val result = repository.getBreedList()
            breedMap.postValue( result?.map {
                it.name to it.id
            }?.toMap())
        }
    }

    fun getCat() = cat

    fun getBreedMap() = breedMap

}