package com.example.blockbuster

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private var movieList : MutableList<Movie>, val clickListener : MovieClickListener) : RecyclerView.Adapter<MoviesViewHolder>() {

    interface MovieClickListener{
        fun listItemClick(movie : Movie)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.poster.setImageResource(movieList.get(position).poster)
        holder.title.text = movieList.get(position).tittle
        holder.genre.text = movieList.get(position).genre
        holder.itemView.setOnClickListener {
            clickListener.listItemClick(movieList[position])
        }
    }
}