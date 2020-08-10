package com.example.catapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Breed(@Json(name = "id")val id : String,@Json(name = "name") val name : String)