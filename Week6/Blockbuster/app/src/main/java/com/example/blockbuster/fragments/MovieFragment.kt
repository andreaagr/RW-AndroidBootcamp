package com.example.blockbuster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.blockbuster.R
import com.example.blockbuster.data.DataManager
import com.example.blockbuster.data.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Show details in the view
        val model = activity?.let {  ViewModelProvider(it).get(DataManager::class.java) }
        if (model != null) showMovieDetails(model.getMovieSelected())

    }

    private fun showMovieDetails(movie: Movie?){
        /*
        if(movie?.poster is Int)
            iv_poster_detail.setImageResource(movie?.poster as Int)
        else if(movie?.poster is Uri)
            iv_poster_detail.setImageURI(movie?.poster as Uri)
        */
        tv_title_details.text = movie!!.title
        tv_date_details.text = movie.releaseDate
        tv_genre_details.text = movie.genre
        tv_summary_details.text = movie.summary
        rb_show_details.rating = movie.stars
    }

}