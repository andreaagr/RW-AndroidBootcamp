package com.example.catapi.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val repository = Injection.providedCatRepository()
    var data : LiveData<List<Cat>>? = null
    var funnyCatList : LiveData<List<FunnyCat>>? = null


   init {
        // ------------Initialize the list when the application loads
        viewModelScope.launch(Dispatchers.IO) {
            data = repository.getCats()
            funnyCatList = repository.getFunnyCats()
        }
   }

    fun addCat(cat: Cat){
        // Adding the element into database
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCat(cat)
        }
    }
}