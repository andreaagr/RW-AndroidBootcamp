package com.example.blockbuster.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataManager(application: Application) : AndroidViewModel(application) {

    private val repository = Injection.providedMovieRepository()
    private var selected : MutableLiveData<Movie> = MutableLiveData()
    private val myPrefs = MyPrefs(application)
    private var isLogged = myPrefs.getPrefs()
    var data = MutableLiveData<MutableList<Movie>>()

    init {
        // ------------Initialize Movie List when the application loads
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repository.getMovies() as MutableList<Movie>)
        }
    }

    fun setMovieSelected(movie: Movie){
        selected.value = movie
    }

    fun getMovieSelected(): Movie? {
        return selected.value
    }

    fun addMovie(movie: Movie){
        // For RecyclerView
        data.value?.add(movie)
        // Adding the element into database
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movie)
        }
    }

    fun setLoggedStatus(status : Boolean){
        isLogged = status
        myPrefs.savePrefs(isLogged)
    }

    fun getLoggedStatus() : Boolean = isLogged

}