import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.catapi.model.FunnyCat
import com.example.catapi.repository.Injection
import com.example.catapi.networking.Failure
import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.Success
import com.example.catapi.networking.buildApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import kotlin.random.Random

const val STATUS_WORK = "Work finished"

@RequiresApi(Build.VERSION_CODES.M)
class SynchronizeDataWorker(context: Context, workerParameters: WorkerParameters) : Worker(context,workerParameters){


    private val repository = Injection.providedCatRepository()

    // ------------------------------------ For Api with List<FunnyCat> adapter
    private val apiServiceCat by lazy {
        val type: Type = Types.newParameterizedType(List::class.java, FunnyCat::class.java)
        val parserM = Moshi.Builder().build()
        val reportAdapter: JsonAdapter<List<FunnyCat>> = parserM.adapter(type)
        buildApiService(parserM)
    }

    private val remoteApiCat by lazy {
        RemoteApi(apiServiceCat)
    }
    //----------------------------------------------------------------------

    // Delete all elements in table funny_cats and stores 10 new images in Room Database
    override fun doWork(): Result {
        return try {
            generateRandomImages()
            Log.e("Work", "Doing work")
            createNotification()
            Result.success(workDataOf(STATUS_WORK to true))
        }catch (error : Throwable){
            Result.failure()
        }

    }

    private fun generateRandomImages(){
        GlobalScope.launch {
            repository.deleteFunnyCats()

            for (i in 0..10){
                val random = Random.nextInt(1,7)
                val result = remoteApiCat.getFunnyPhoto(random.toString())

                if(result is Success){
                    repository.addFunnyCat(result.data[0])
                }else if(result is Failure)
                    println(result.error)
            }
        }
    }

    private fun createNotification() {

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("101", "channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "101")
            .setContentTitle("Synchronizing")
            .setContentText("New cats available!")
            .setSmallIcon(R.drawable.ic_menu_gallery)

        notificationManager.notify(1, notificationBuilder.build())

    }
}