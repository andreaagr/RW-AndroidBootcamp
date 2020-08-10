import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.catapi.networking.Failure
import com.example.catapi.networking.RemoteApi
import com.example.catapi.networking.Success
import com.example.catapi.repository.AppRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.M)
class SynchronizeDataWorker(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context,workerParameters), KoinComponent{

    private val repository : AppRepository by inject()
    private val remoteApiCat : RemoteApi by inject()

    companion object {
        const val Progress = "Progress"
    }


    // Delete all elements in table funny_cats and stores 10 new images in Room Database
    override suspend fun doWork(): Result {
        return try {

            setProgress(workDataOf(Progress to 0))
            generateRandomImages()
            setProgress(workDataOf(Progress to 100))
            Result.success()

        }catch (error : Throwable){
            Result.failure()
        }

    }

    private suspend fun generateRandomImages(){

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