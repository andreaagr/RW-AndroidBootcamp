package com.example.catapi.ui

import SynchronizeDataWorker
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.example.catapi.R
import com.example.catapi.model.FunnyCat
import com.example.catapi.model.MyViewModel
import com.example.catapi.recyclerview.funnycat.FunnyCatAdapter
import kotlinx.android.synthetic.main.fragment_funny_cats.*
import java.util.concurrent.TimeUnit


class FunnyCatsFragment : Fragment() {

    private val model by lazy {
        activity?.let { ViewModelProvider(it).get(MyViewModel::class.java) }!!
    }

    private val adapter by lazy{
        FunnyCatAdapter(listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        synchronizeApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model.funnyCatList?.observe(viewLifecycleOwner, Observer {
            model.funnyCatList!!.value?.let { it1 -> adapter.updateList(it1) }
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_funny_cats, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //-----------------------------------------------Setting the Recycler View
        val numberOfColumns = 2
        rv_funny_cats.layoutManager = GridLayoutManager(activity, numberOfColumns)
        rv_funny_cats.adapter = adapter
        //------------------------------------------------------------------------

    }

    private fun synchronizeApi(){

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val work = PeriodicWorkRequestBuilder<SynchronizeDataWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val workManager = activity?.baseContext?.let { WorkManager.getInstance(it) }
        workManager?.enqueueUniquePeriodicWork("sync", ExistingPeriodicWorkPolicy.KEEP,work)
    }
}