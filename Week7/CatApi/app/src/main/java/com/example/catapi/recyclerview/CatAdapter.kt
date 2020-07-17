package com.example.catapi.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapi.R
import com.example.catapi.model.Cat


class CatAdapter(private var catList : MutableList<Cat>) : RecyclerView.Adapter<CatViewHolder>() {

    interface CatClickListener{
        fun listItemClick(movie : Cat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item,parent,false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val url = catList[position].url
        val breed = "Breed: "+catList[position].breed
        val genre = "Genre: "+catList[position].sex
        println(catList[position].sex)
        holder.name.text = catList[position].name
        holder.breed.text = breed
        holder.genre.text = genre
        if(url.isNotEmpty())
            Glide.with(holder.itemView).load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photo)

        /*
        holder.itemView.setOnClickListener {
            clickListener.listItemClick(catList[position])
        }*/
    }


    fun updateCatList(newCatList : MutableList<Cat>){
        val diffCallback =
            CatDiffCallback(
                catList,
                newCatList
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        catList.clear()
        catList.addAll(newCatList)
        diffResult.dispatchUpdatesTo(this)
    }

}

