package com.example.catapi.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val repository = Injection.providedMovieRepository()
    var data : MutableLiveData<MutableList<Cat>> = MutableLiveData(mutableListOf<Cat>())


   init {
        // ------------Initialize the list when the application loads
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repository.getCats() as MutableList<Cat>)
        }
   }

    fun addCat(cat: Cat){
        // For RecyclerView
        data.value?.add(cat)
        // Adding the element into database
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCat(cat)
        }
    }
}