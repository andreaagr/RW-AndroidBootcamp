package com.example.catapi.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapi.R
import com.example.catapi.model.Cat
import com.example.catapi.model.MyViewModel
import com.example.catapi.recyclerview.cat.CatAdapter
import kotlinx.android.synthetic.main.fragment_first.*

class ShowCatstFragment : Fragment() {

    private val model by lazy {
        activity?.let { ViewModelProvider(it).get(MyViewModel::class.java) }!!
    }

    private val adapter =
        CatAdapter(mutableListOf())

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // If exist any change on Recycler View's data, then update the view
        model.data?.observe(viewLifecycleOwner, Observer {
            adapter.updateCatList(it as MutableList<Cat>)
        })
        // Let you inflate a options menu. If you delete the line, the menu isn't show
        setHasOptionsMenu(true)
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

        fab_add_cat.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_show_more-> {
                findNavController().navigate(R.id.action_FirstFragment_to_funnyCatsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}