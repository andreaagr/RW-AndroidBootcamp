package com.example.blockbuster.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blockbuster.R
import com.example.blockbuster.data.DataManager
import com.example.blockbuster.data.Movie
import com.example.blockbuster.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.switch_layout.view.*


class MainFragment : Fragment() , MovieAdapter.MovieClickListener {

    private lateinit var model : DataManager
    private val adapter = MovieAdapter(mutableListOf(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // -------------------------------------Show toolbar
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.show()
        // -------------------------------------------------
        // -------------------------------------------------------Initialize Movie List
        model = activity.let { ViewModelProvider(it).get(DataManager::class.java) }
        //----------------------------------------------------------------------------
        // --------------------------------------------------------------OnBackPressed
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    createExitAlert(activity)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
        //---------------------------------------------------------------------------
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Let you inflate an options menu. If you delete the line, the menu isn't show
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //---------------------------------Setting the Recycler View
        val numberOfColumns = 2
        rv_movies.layoutManager = GridLayoutManager(activity, numberOfColumns)
        rv_movies.adapter = adapter
        //----------------------------------------------------------

        // If exist any change on Recycler View's data, then update the view
        model.data.observe(viewLifecycleOwner, Observer {
            adapter.updateMovieList(it)
            //---------------------------------------Show favorite movie
            
            val movie = model.data.value?.maxBy { movie -> movie.stars  }

            if(movie != null){
                /*if(movie.poster is Int)
                    iv_fav_poster.setImageResource(movie.poster as Int)
                else if(movie.poster is Uri)
                    iv_fav_poster.setImageURI(movie.poster as Uri)*/
                tv_title_itemf.text = movie.title
                rb_show_stars.rating = movie.stars
            }
            //----------------------------------------------------------
        })

        // Action for the floating action button
        fab.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_addMovieFragment)
        }
    }

    override fun listItemClick(movie: Movie) {
        model.setMovieSelected(movie)
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_movieFragment)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        // Set the status in the switch and add a listener
        val item = menu.findItem(R.id.switch_menu)
        item.setActionView(R.layout.switch_layout)
        item.actionView.switch_logout.isChecked = model.getLoggedStatus()
        item.actionView.switch_logout.setOnCheckedChangeListener {_, isChecked ->
            if (!isChecked)
                model.setLoggedStatus(false)
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

    }

    fun createExitAlert(activity: AppCompatActivity){
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> activity.finish()
                    DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
                }
            }

        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Do you want to exit?")
        builder.setPositiveButton("Accept", dialogClickListener)
        builder.setNegativeButton("Cancel",dialogClickListener)

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}