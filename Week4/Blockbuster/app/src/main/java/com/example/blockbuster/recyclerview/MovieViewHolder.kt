package com.example.blockbuster.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blockbuster.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var poster = itemView.findViewById<ImageView>(R.id.iv_item_poster)
    var title = itemView.findViewById<TextView>(R.id.tv_title_item)
    var genre = itemView.findViewById<TextView>(R.id.tv_genre_item)

}