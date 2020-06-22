package com.example.blockbuster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    //private lateinit var movieSelected : Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recover the movie selected
        //movieSelected = arguments?.getSerializable("movie") as Movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Show details in the view
        val model = activity?.let {  ViewModelProvider(it).get(DataManager::class.java) }
        if (model != null) imageView.setImageResource(model.getMovieSelected()?.poster as Int)


    }


}