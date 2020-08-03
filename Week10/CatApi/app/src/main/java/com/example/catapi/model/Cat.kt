package com.example.catapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "cat_table")
data class Cat(
    @Json(name = "url")var url : String
){
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0

    var catName = ""
    var breed = ""
    var sex = ""

    constructor(catName : String,breed : String, sex : String, url : String) : this(url){
        this.catName = catName
        this.breed = breed
        this.sex = sex
        this.url = url
    }
}