package com.example.catapi.recyclerview.funnycat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapi.R
import com.example.catapi.model.FunnyCat

class FunnyCatAdapter(private var funnyCatList : List<FunnyCat>) : RecyclerView.Adapter<FunnyCatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunnyCatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.funny_cat_item,parent,false)
        return FunnyCatViewHolder(view)
    }

    override fun getItemCount() = funnyCatList.size

    override fun onBindViewHolder(holder: FunnyCatViewHolder, position: Int) {
        val url = funnyCatList[position].url
        if(url.isNotEmpty())
            Glide.with(holder.itemView).load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.picture)
    }

    fun updateList(newList : List<FunnyCat>){
        funnyCatList = newList
        notifyDataSetChanged()
    }
}