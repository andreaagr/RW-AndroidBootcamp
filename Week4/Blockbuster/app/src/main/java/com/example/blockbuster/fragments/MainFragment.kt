package com.example.blockbuster.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockbuster.R
import com.example.blockbuster.data.DataManager
import com.example.blockbuster.data.Movie
import com.example.blockbuster.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() ,
    MovieAdapter.MovieClickListener {

    private lateinit var model : DataManager
    private var movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // -------------------------------------------------------Initialize Movie List
        model = activity?.let {  ViewModelProvider(it).get(DataManager::class.java) }!!
        model.initMovieList()
        movieList = model.getMovieList()!!
        //-----------------------------------------------------------------------------
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Let you inflate a options menu. If you delete the line, the menu isn't show
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        // If exist any change on Recycler View's data then update the view
        model.data.observe(viewLifecycleOwner,
            Observer { rv_movies.adapter?.notifyDataSetChanged() })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //---------------------------------------Show favorite movie
        val movie = movieList.maxBy { movie -> movie.stars }
        if(movie?.poster is Int)
            iv_fav_poster.setImageResource(movie.poster as Int)
        else if(movie?.poster is Uri)
            iv_fav_poster.setImageURI(movie.poster as Uri)
        tv_title_itemf.text = movie!!.title
        rb_show_stars.rating = movie.stars
        //----------------------------------------------------------
        //---------------------------------Setting the Recycler View
        //val numberOfColumns = 2
        //rv_movies.layoutManager = GridLayoutManager(activity, numberOfColumns)
        rv_movies.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter =
            MovieAdapter(movieList, this)
        rv_movies.adapter = adapter
        //----------------------------------------------------------
        fab.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_addMovieFragment)
        }
    }

    override fun listItemClick(movie: Movie) {
        model.setMovieSelected(movie)
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_movieFragment)
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }*/

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_genre -> true
            R.id.option_stars -> movieList.sortedBy { movie -> movie.stars }.reversed()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }*/
}