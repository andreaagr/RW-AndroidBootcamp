package com.example.blockbuster.recyclerview

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blockbuster.data.Movie
import com.example.blockbuster.R

class MovieAdapter(private var movieList : MutableList<Movie>, private val clickListener : MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieClickListener{
        fun listItemClick(movie : Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val element = movieList[position]
        //---- Two ways to load an image, one for drawable resources and another for the elements added
        if(element.poster is Int){
            holder.poster.setImageResource(element.poster as Int)
        }else if(element.poster is Uri){
            holder.poster.setImageURI(element.poster as Uri)
        }
        //---------------------------------------------------------------------------------------------
        holder.title.text = element.title

        holder.itemView.setOnClickListener {
            clickListener.listItemClick(movieList[position])
        }

        holder.rating.rating = element.stars
    }
}