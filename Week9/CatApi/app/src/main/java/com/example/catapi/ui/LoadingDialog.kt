package com.example.catapi.ui

import android.app.AlertDialog
import androidx.fragment.app.FragmentActivity

import com.example.catapi.R


class LoadingDialog(private val activity: FragmentActivity) {
        private val alertDialog  by lazy { createLoadingDialog() }

        private fun createLoadingDialog() : AlertDialog{
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.custom_dialog, null))
            builder.setCancelable(false)
            return builder.create()
        }

        fun startDialog(){
            alertDialog.show()
        }

        fun dismissDialog() {
            alertDialog.dismiss()
        }
}