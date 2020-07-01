package com.example.blockbuster.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.blockbuster.data.Movie

class MovieDiffCallback(private val oldList : List<Movie>,
                        private val newList : List<Movie>) : DiffUtil.Callback()
{
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size


    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return  oldMovie.poster == newMovie.poster
                && oldMovie.genre == newMovie.genre
                && oldMovie.releaseDate == newMovie.releaseDate
                && oldMovie.stars == newMovie.stars
                && oldMovie.summary == newMovie.summary
                && oldMovie.title == newMovie.title
    }
}