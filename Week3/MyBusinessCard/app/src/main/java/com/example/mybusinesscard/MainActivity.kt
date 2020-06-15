package com.example.mybusinesscard

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat.getRotation
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.content_main.*
import android.view.Surface.ROTATION_90
import android.view.Surface.ROTATION_180
import android.view.Surface.ROTATION_0
import android.content.Context.WINDOW_SERVICE
import android.os.Build
import android.view.Surface
import androidx.core.content.ContextCompat.getSystemService
import android.view.WindowManager
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    var isShown = false
    var firstTime = true
    var constraintSetShow = ConstraintSet()
    var constraintSetHide = ConstraintSet()


    @RequiresApi(Build.VERSION_CODES.KITKAT)
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

            //Check device's position
            if(getRotation(getApplicationContext()).equals("horizontal")){
                if(firstTime){
                    //--------------------------------------------------------------------------------------For the animation
                    constraintSetHide.clone(layout_hide)
                    constraintSetShow.clone(this, R.layout.content_main_detail)

                    //-------------------------------------------------------------------------------------------------------
                }
                handleShowDetails()
            }

        })
        //-------------------------------------------------------------------------------------------------------


    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun handleShowDetails(){
        TransitionManager.beginDelayedTransition(layout_hide)
        if(isShown){
            //Hide details
            constraintSetHide.applyTo(layout_hide)

        }else{

            constraintSetShow.applyTo(layout_hide)

        }
        isShown = !isShown

    }


    fun getRotation(context: Context): String {
        val rotation = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation
        when (rotation) {
            Surface.ROTATION_0, Surface.ROTATION_180 -> return "vertical"
            Surface.ROTATION_90 -> return "horizontal"
            else -> return "horizontal"
        }
    }

}
