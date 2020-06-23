package com.example.blockbuster.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blockbuster.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var poster: ImageView = itemView.findViewById(R.id.iv_item_poster)
    var title: TextView = itemView.findViewById(R.id.tv_title_item)
    var rating : RatingBar = itemView.findViewById(R.id.rb_item)
}