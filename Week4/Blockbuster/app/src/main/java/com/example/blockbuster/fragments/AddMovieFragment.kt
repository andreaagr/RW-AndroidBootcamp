package com.example.blockbuster.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.blockbuster.data.DataManager
import com.example.blockbuster.data.Movie
import com.example.blockbuster.R
import kotlinx.android.synthetic.main.fragment_add_movie.*

class AddMovieFragment : Fragment() {
    private var imageResource : Uri? = null
    private lateinit var model : DataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_movie, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.let { ViewModelProvider(it).get(DataManager::class.java) }!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_add_movie.setOnClickListener {
            if(missingFields())
                Toast.makeText(activity,"There are some missing fields...", Toast.LENGTH_SHORT).show()
            else {
                // When the button is clicked a new movie is added to the Recycler View
                val newMovie = Movie(
                    model.data.value?.size!!,
                    et_date.text.toString(),
                    et_movie_title.text.toString(),
                    et_summary.text.toString(),
                    imageResource,
                    et_genre.text.toString(),
                    rb_choose_stars.rating
                )
                // Add the movie in the Recycler View
                model.addElement(newMovie)
                // Show a message
                Toast.makeText(activity, "A new item has been added!", Toast.LENGTH_SHORT).show()
                // Create another fragment
                view.findNavController().navigate(R.id.action_addMovieFragment_self)
            }
        }

        iv_add_image.setOnClickListener {
            chooseImage()
        }
    }

    private fun chooseImage(){
        val intent= Intent()
        intent.type= "image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Choose an image..."),
            IMAGE_CODE
        )
    }

    private fun missingFields() : Boolean{
        return (et_movie_title.text.isEmpty() || et_genre.text.isEmpty() || et_date.text.isEmpty())
    }


    companion object {
        private const val IMAGE_CODE=123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(Activity.RESULT_OK == resultCode){
            if(IMAGE_CODE == requestCode){
                // Obtains the Uri of the image
                imageResource = data?.data
                // Show the image in the layout
                iv_add_image.setImageURI(imageResource)
            }
        }
    }

}