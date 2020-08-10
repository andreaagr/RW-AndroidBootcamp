package com.example.catapi.recyclerview.cat

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cat_item.view.*

class CatViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.tv_name_cat
    val breed : TextView = itemView.tv_breed_cat
    val genre : TextView = itemView.tv_genre_cat
    val photo : ImageView = itemView.iv_cat_image
}