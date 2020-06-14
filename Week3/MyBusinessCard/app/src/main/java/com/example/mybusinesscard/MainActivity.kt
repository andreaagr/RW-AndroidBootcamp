package com.example.mybusinesscard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //------------------------------------------------------------------------------Hide the title of the app
        toolbar.title = ""
        toolbar.subtitle = ""
        //-------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------Set the toolbar of our application
        setSupportActionBar(toolbar)
        //-------------------------------------------Hides the profile image when the collapsing bar is collapsed
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                img_profile.visibility= View.INVISIBLE


            } else {
                //Expanded
                img_profile.visibility= View.VISIBLE
            }
        })
        //-------------------------------------------------------------------------------------------------------

    }
}
