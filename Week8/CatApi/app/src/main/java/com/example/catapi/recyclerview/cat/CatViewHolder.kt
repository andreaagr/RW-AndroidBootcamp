package com.example.catapi.recyclerview.cat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cat_item.view.*

class CatViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.tv_name_cat
    val breed = itemView.tv_breed_cat
    val genre = itemView.tv_genre_cat
    val photo = itemView.iv_cat_image
}