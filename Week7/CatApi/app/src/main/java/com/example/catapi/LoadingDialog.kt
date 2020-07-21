package com.example.catapi

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater

import androidx.fragment.app.FragmentActivity


class LoadingDialog(private val activity: FragmentActivity) {
        private val alertDialog  by lazy { createLoadingDialog() }

        private fun createLoadingDialog() : AlertDialog{
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.custom_dialog, null))
            builder.setCancelable(false)
            return builder.create()
            //alertDialog = builder.create()
            //alertDialog.show()
        }

        fun startDialog(){
            alertDialog.show()
        }

        fun dismissDialog() {
            alertDialog.dismiss()
        }

}