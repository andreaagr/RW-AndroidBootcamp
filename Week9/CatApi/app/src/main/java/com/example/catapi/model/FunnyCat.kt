package com.example.catapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "funny_cat_table")
data class FunnyCat(
    val url : String
){
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0
}