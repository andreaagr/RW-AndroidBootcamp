package com.example.catapi.ui

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapi.R
import com.example.catapi.model.Breed
import com.example.catapi.model.Cat
import com.example.catapi.model.MyViewModel
import com.example.catapi.model.Success
import com.example.catapi.networking.NetworkStatusChecker
import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.buildApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Type

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@RequiresApi(Build.VERSION_CODES.M)
class SecondFragment : Fragment() {

    private lateinit var breedMap : Map<String,String>
    private var image = ""
    //val model: MyViewModel by viewModels()
    private lateinit var model : MyViewModel

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    // ------------------------------------ For Api with List<Breed> adapter
    private val apiServiceBreed by lazy {
        val type: Type = Types.newParameterizedType(List::class.java, Breed::class.java)
        val parserM = Moshi.Builder().build()
        val reportAdapter: JsonAdapter<List<Breed>> = parserM.adapter(type)
        buildApiService(parserM)
    }

    private val remoteApiBreed by lazy {
        RemoteApi(apiServiceBreed)
    }
    //----------------------------------------------------------------------

    // ------------------------------------ For Api with List<Cat> adapter
    private val apiServiceImage by lazy {

        val type: Type = Types.newParameterizedType(List::class.java, Cat::class.java)
        val parserM = Moshi.Builder().build()
        val reportAdapter: JsonAdapter<List<Cat>> = parserM.adapter(type)
        buildApiService(parserM)

    }

    private val remoteApiImage by lazy {
        RemoteApi(apiServiceImage)
    }
    //----------------------------------------------------------------------

    private val genreList = arrayOf("Male","Female")

    private fun obtainBreedList(){
        GlobalScope.launch(Dispatchers.Main) {
            networkStatusChecker.performIfConnectedToInternet {
                val result = remoteApiBreed.getBreedList()
                if (result is Success) {
                    breedMap = result.data.map { it.name to it.id }.toMap()

                    //-----------------------------------------------Display the breeds available in the spinner
                    val breedAdapter = activity?.let {
                        ArrayAdapter(
                            it,
                            android.R.layout.simple_spinner_dropdown_item,
                            breedMap.keys.toTypedArray()
                        )
                    }
                    breedAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sp_breed.adapter = breedAdapter
                    //------------------------------------------------------------------------------------------
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.let { ViewModelProvider(it).get(MyViewModel::class.java) }!!
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ---------------------------------------------------------------------------Show options in the spinner genre
        val genreAdapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                genreList
            )
        }
        genreAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_genre.adapter = genreAdapter
        obtainBreedList()
        //-------------------------------------------------------------------------------------------------------------

        //----------------------------------- When the image view is tapped, new cat of the breed selected is generated
        iv_cat_random.setOnClickListener {
            searchCat(sp_breed.selectedItem.toString())
        }
        //-------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------ Button for add a new cat
        bt_new_cat.setOnClickListener {
            if (!emptyName()){
                val cat = Cat(et_cat_name.text.toString(),sp_breed.selectedItem.toString(),sp_genre.selectedItem.toString(),image)
                // Add changes in the database
                println("Gatos en second ${model.data.value}")
                model.addCat(cat)

                // Show a message
                Toast.makeText(activity, "A new item has been added!", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_SecondFragment_self)

                println("Gatos actualizados ${model.data.value}")


            }else
                Toast.makeText(activity,"The cat must have a name!",Toast.LENGTH_SHORT).show()
        }
        //-------------------------------------------------------------------------------------------------------------
    }

    private fun emptyName() : Boolean{
        return et_cat_name.text.isEmpty()
    }


    private fun searchCat(breedSelected : String){
        GlobalScope.launch(Dispatchers.Main) {
            networkStatusChecker.performIfConnectedToInternet {

                val result = breedMap[breedSelected]?.let { remoteApiImage.getCatImage(it) }

                if(result is Success){
                    image = result.data[0].url

                    activity?.let {
                        Glide.with(it).load(image)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(iv_cat_random)
                    }
                }
            }
        }
    }
}