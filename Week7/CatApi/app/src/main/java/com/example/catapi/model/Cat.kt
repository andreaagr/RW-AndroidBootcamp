package com.example.catapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_table")
data class Cat(
    val name : String,
    val breed : String,
    val sex : String,
    val url : String
){
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0
}