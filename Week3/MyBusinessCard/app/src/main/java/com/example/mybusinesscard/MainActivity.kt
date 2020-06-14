package com.example.mybusinesscard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //---------------------Hide the title of the app
        toolbar.title = ""
        toolbar.subtitle = ""
        //----------------------------------------------
        // Set the toolbar of our application
        setSupportActionBar(toolbar)

    }
}
