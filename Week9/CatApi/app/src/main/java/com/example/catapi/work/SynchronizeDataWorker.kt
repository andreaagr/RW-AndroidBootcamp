import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.catapi.App
import com.example.catapi.networking.Failure
import com.example.catapi.networking.Success
import com.example.catapi.repository.Injection
import kotlin.random.Random

const val STATUS_WORK = "Work finished"

@RequiresApi(Build.VERSION_CODES.M)
class SynchronizeDataWorker(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context,workerParameters){

    private val repository = Injection.providedCatRepository()
    private val remoteApiCat = App.remoteApiCat

    companion object {
        const val Progress = "Progress"
    }


    // Delete all elements in table funny_cats and stores 10 new images in Room Database
    override suspend fun doWork(): Result {
        return try {

            setProgress(workDataOf(Progress to 0))
            generateRandomImages()
            setProgress(workDataOf(Progress to 100))
            Result.success(workDataOf(STATUS_WORK to true))

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