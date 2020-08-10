package com.example.catapi.ui.fragments

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapi.R
import com.example.catapi.viewmodel.UIResponseState
import com.example.catapi.model.Cat
import com.example.catapi.networking.NetworkStatusChecker
import com.example.catapi.ui.LoadingDialog
import com.example.catapi.viewmodel.AddCatViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.M)
class AddCatFragment : Fragment() {

    private val model : AddCatViewModel by viewModel()
    private var breedMap = mapOf<String,String>()
    private var image = ""
    private val loadingDialog by lazy { activity?.let { LoadingDialog(it) }}
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setupObserver()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupGenreAdapter()
        populateBreedMap()

        //----------------------------------- When the image view is tapped, new cat of the breed selected is generated
        iv_cat_random.setOnClickListener {
            showCatPhoto()
        }
        //-------------------------------------------------------------------------------------------------------------

        //------------------------------------------------------------------------------------ Button for add a new cat
        bt_new_cat.setOnClickListener {
            val cat = Cat(et_cat_name.text.toString()
                ,sp_breed.selectedItem.toString()
                ,sp_genre.selectedItem.toString(),image
                )

            if (!model.emptyName(cat)){
                // Add changes in the database
                model.addCat(cat)
                // Show a message
                Toast.makeText(activity, "A new item has been added!", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_SecondFragment_self)
            }else
                Toast.makeText(activity,"The cat must have a name!",Toast.LENGTH_SHORT).show()
        }
        //-------------------------------------------------------------------------------------------------------------
    }

    private fun setupObserver(){

        model.viewState.observe(viewLifecycleOwner, Observer {uiState ->
            when(uiState){
                is UIResponseState.Loading -> loadingDialog?.startDialog()

                is UIResponseState.Error -> {
                    Toast.makeText(activity,"Failed to load resources :(",Toast.LENGTH_SHORT).show()
                    loadingDialog?.dismissDialog()
                }

                is UIResponseState.Success<*> -> {
                    loadingDialog?.dismissDialog()
                    println("Success")
                    if (uiState.content is Cat){
                        image = uiState.content.url

                        if(image != "")
                            activity?.let { it1 ->
                                Glide.with(it1).load(image)
                                    .centerCrop()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(iv_cat_random) }

                    }else if(uiState.content is Map<*,*>){
                        breedMap = uiState.content as Map<String, String>

                        println(breedMap)
                        val breedAdapter = activity?.let {
                            ArrayAdapter(
                                it,
                                android.R.layout.simple_spinner_dropdown_item,
                                breedMap.keys.toTypedArray()
                            )
                        }
                        breedAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        sp_breed.adapter = breedAdapter
                    }

                }
                else ->{
                    println("Waiting for new actions")
                }
            }
        })
    }

    private fun setupGenreAdapter(){
        // ---------------------------------------------------------------------------Show options in the spinner genre
        val genreAdapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                arrayOf("Male","Female")
            )
        }
        genreAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_genre.adapter = genreAdapter
    }

    private fun populateBreedMap(){
        if(!networkStatusChecker.performIfConnectedToInternet {
                model.obtainBreedList()
            }) {
            loadingDialog?.dismissDialog()
            Toast.makeText(activity,"Some functions would be no available without internet connection :(", Toast.LENGTH_SHORT).show()
            val breedAdapter = activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_dropdown_item,
                    arrayOf("Nothing to show")
                )
            }
            breedAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_breed.adapter = breedAdapter
        }
    }

    private fun showCatPhoto(){
        if (!networkStatusChecker.performIfConnectedToInternet {
                breedMap[sp_breed.selectedItem.toString()]?.let { it1 -> model.searchCat(it1) }
            })
            Toast.makeText(activity,"You don't have internet connection! :(", Toast.LENGTH_SHORT).show()
    }
}


