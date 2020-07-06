package com.example.blockbuster.room

import android.net.Uri
import androidx.room.TypeConverter


class ImageConverter {
    @TypeConverter
    fun convertImage(image : Any?) : String?{
        if(image != null)
            return image.toString()

        return null
    }

    @TypeConverter
    fun toImage(value : String?) : Any?{
        return try{
            Uri.parse(value)
        }catch (exception : Exception){
            value?.toInt()
        }
    }

}