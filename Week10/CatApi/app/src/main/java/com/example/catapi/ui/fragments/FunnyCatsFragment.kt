package com.example.catapi.ui.fragments

import SynchronizeDataWorker
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.example.catapi.R
import com.example.catapi.recyclerview.funnycat.FunnyCatAdapter
import com.example.catapi.ui.LoadingDialog
import com.example.catapi.viewmodel.FunnyCatsViewModel
import kotlinx.android.synthetic.main.fragment_funny_cats.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class FunnyCatsFragment : Fragment() {
    private val model : FunnyCatsViewModel by viewModel()
    private val adapter by lazy{
        FunnyCatAdapter(listOf())
    }

    private val loadingDialog by lazy { activity?.let { LoadingDialog(it) }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        synchronizeApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model.getFunnyCats()?.observe(viewLifecycleOwner, Observer {
            model.getFunnyCats()!!.value?.let { it1 -> adapter.updateList(it1) }
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
        workManager?.getWorkInfoByIdLiveData(work.id)?.observe(this, Observer { workInfo: WorkInfo? ->
            if (workInfo != null) {
                when(workInfo.progress.getInt("Progress", 100)){
                    0 -> loadingDialog?.startDialog()
                    100 -> {
                        createNotification()
                        loadingDialog?.dismissDialog()
                    }
                }
            }
        })
    }


    private fun createNotification() {

        val notificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("101", "channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(requireActivity().applicationContext, "101")
            .setContentTitle("Synchronizing")
            .setContentText("New cats available!")
            .setSmallIcon(R.drawable.cat_profile)

        notificationManager.notify(1, notificationBuilder.build())

    }
}