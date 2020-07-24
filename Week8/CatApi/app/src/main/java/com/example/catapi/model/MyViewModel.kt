package com.example.catapi.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val repository = Injection.providedCatRepository()
    var data : LiveData<List<Cat>>? = null


   init {
        // ------------Initialize the list when the application loads
        viewModelScope.launch(Dispatchers.IO) {
            data = repository.getCats()
        }
   }

    fun addCat(cat: Cat){
        // Adding the element into database
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCat(cat)
        }
    }
}