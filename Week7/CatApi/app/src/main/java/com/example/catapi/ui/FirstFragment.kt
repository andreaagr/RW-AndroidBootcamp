package com.example.catapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapi.R
import com.example.catapi.model.Cat
import com.example.catapi.model.MyViewModel
import com.example.catapi.recyclerview.CatAdapter
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    //val model: MyViewModel by viewModels()
    private lateinit var model : MyViewModel
    private val adapter = CatAdapter(mutableListOf())
    //private var catList = mutableListOf<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.let { ViewModelProvider(it).get(MyViewModel::class.java) }!!
        //catList = model.data.value!!

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //---------------------------------Setting the Recycler View
        val numberOfColumns = 2
        rv_cats.layoutManager = GridLayoutManager(activity, numberOfColumns)

        rv_cats.adapter = adapter
        //----------------------------------------------------------
        println("Gatos")
        println("Gatos ${model.data.value}")
        // If exist any change on Recycler View's data, then update the view
        model.data.observe(viewLifecycleOwner, Observer {
            adapter.updateCatList(it)
        })

        fab_add_cat.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


}