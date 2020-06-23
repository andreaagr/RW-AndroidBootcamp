package com.example.blockbuster.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blockbuster.R

class DataManager : ViewModel() {
    private var selected : MutableLiveData<Movie> = MutableLiveData()
    var data : MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()

    fun setMovieSelected(movie: Movie){
        selected.value = movie
    }

    fun getMovieSelected(): Movie? {
        return selected.value
    }

    fun initMovieList(){
        // Init the list with 2 elements
        data.value = mutableListOf(
            Movie(
                0,
                "",
                "Howl's Moving Castle",
                "",
                R.drawable.movie1,
                "Animation",
                4.5f
            ),
            Movie(
                1,
                "",
                "Spirited away",
                "",
                R.drawable.movie2,
                "Animation",
                3.5f
            )
        )
    }

    fun addElement(movie: Movie){
        data.value?.add(movie)
    }

    fun getMovieList(): MutableList<Movie>? {
        return data.value
    }


}