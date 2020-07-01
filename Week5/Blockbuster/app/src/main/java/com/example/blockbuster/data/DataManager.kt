package com.example.blockbuster.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.blockbuster.MyPrefs

class DataManager(application: Application) : AndroidViewModel(application) {

    private val repository = Injection.providedMovieRepository()
    private var selected : MutableLiveData<Movie> = MutableLiveData()
    private val allMovies = repository.getMovies()
    var data = MutableLiveData<MutableList<Movie>>()
    //var isLogged = MutableLiveData<Boolean>()
    private var myPrefs = MyPrefs(application)
    private var isLogged = myPrefs.getPrefs()

    fun setMovieSelected(movie: Movie){
        selected.value = movie
    }

    fun getMovieSelected(): Movie? {
        return selected.value
    }

    fun initMovieList(){
        // ------------Initialize Movie List when the application loads
        data.value = allMovies as MutableList<Movie>
    }

    fun addMovie(movie: Movie){
        // For RecyclerView
        data.value?.add(movie)
        // Adding the element into database
        repository.addMovie(movie)
    }

    fun setLoggedStatus(status : Boolean){
        isLogged = status
        myPrefs.savePrefs(isLogged)
    }

    fun getLoggedStatus() : Boolean = isLogged
}