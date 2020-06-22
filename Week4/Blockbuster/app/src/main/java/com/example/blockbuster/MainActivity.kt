package com.example.blockbuster
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(),MoviesAdapter.MovieClickListener {

    private var movieList = mutableListOf(
        Movie
            (0,null,"Howl's Moving Castle","",R.drawable.movie1,"Animation", 0.0f),
        Movie(1,null,"Spirited away","",R.drawable.movie2,"Animation",0.0f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

        //---------------------------------Setting the Recycler View
        val numberOfColumns = 2
        rv_movies.layoutManager = GridLayoutManager(this, numberOfColumns)
        val adapter = MoviesAdapter(movieList,this)
        //adapter.setClickListener(this)
        rv_movies.adapter = adapter
        //----------------------------------------------------------
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun listItemClick(movie: Movie) {
        Toast.makeText(this,movie.tittle, Toast.LENGTH_SHORT).show()
    }
}
