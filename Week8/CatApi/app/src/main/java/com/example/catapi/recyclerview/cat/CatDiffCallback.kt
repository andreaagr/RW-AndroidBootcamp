package com.example.catapi.recyclerview.cat

import androidx.recyclerview.widget.DiffUtil
import com.example.catapi.model.Cat

class CatDiffCallback (private val oldList : List<Cat>,
                       private val newList : List<Cat>) : DiffUtil.Callback()
{
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size


    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCat = oldList[oldItemPosition]
        val newCat = newList[newItemPosition]
        return  oldCat.name == newCat.name
                && oldCat.breed == newCat.breed
                && oldCat.sex == newCat.sex
                && oldCat.url == newCat.url

    }
}