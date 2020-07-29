package com.example.catapi.recyclerview.funnycat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.funny_cat_item.view.*

class FunnyCatViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    var picture = itemView.iv_funny_cat_image
}