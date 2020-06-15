package com.example.mybusinesscard

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.content_main.*
import android.os.Build
import android.util.Log
import android.view.Surface
import android.view.WindowManager
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
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
        //-------------------------------------------------------------------When the state of the appbar changes
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            //---------------------------------------------------------------------------Check device's position
            if(getRotation(getApplicationContext()).equals("horizontal")){
                if(firstTime){
                //----------------------------------------------------For the animation
                    //Define how the constraints will be before and after the animation
                    constraintSetHide.clone(layout_hide)
                    constraintSetShow.clone(this, R.layout.content_main_detail)
                    //We only need to define the sets one time
                    firstTime = false
                //---------------------------------------------------------------------
                }
                TransitionManager.beginDelayedTransition(layout_hide)
                if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    //Totally collapsed -> Show details
                    constraintSetShow.applyTo(layout_hide)
                    println("Collapsed")
                }else if(Math.abs(verticalOffset) == 0){
                    //Totally expanded -> Hide details
                    constraintSetHide.applyTo(layout_hide)
                    println("Totally expanded")

                }
            }else if(getRotation(getApplicationContext()).equals("vertical"))
                //We need to define the sets each time the orientation changes to horizontal
                firstTime = true
            //---------------------------------------------------------------------------------------------------


            //---------------------------------------Hides the profile image when the collapsing bar is collapsed
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                img_profile.visibility= View.INVISIBLE

            } else if(Math.abs(verticalOffset) == 0){
                // Totally Expanded
                img_profile.visibility= View.VISIBLE
            }

            Log.d("Appbar", verticalOffset.toString())
            //---------------------------------------------------------------------------------------------------
        })
        //-------------------------------------------------------------------------------------------------------
        iv_github.setOnClickListener{
            showInfo("Github")

        }

        iv_linkedin.setOnClickListener {
            showInfo("Linkedin")
        }

        iv_twitter.setOnClickListener {
            showInfo("Twitter")
        }

    }

    fun getRotation(context: Context): String {
        val rotation = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation
        when (rotation) {
            Surface.ROTATION_0, Surface.ROTATION_180 -> return "vertical"
            Surface.ROTATION_90 -> return "horizontal"
            else -> return "horizontal"
        }
    }

    fun showInfo(option : String){
        val value = when(option){
            "Twitter"-> R.string.twitter
            "Github" -> R.string.telegram
            "Linkedin" -> R.string.linkedin
            else -> 0
        }

        val dialogMessage = getString(value)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(option)
        builder.setMessage(dialogMessage)
        builder.create().show()

    }

}
