package unifedideas.ama.gamekotlin.app

import android.app.Application
import androidx.room.Room
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.orhanobut.hawk.Hawk
import unifedideas.ama.gamekotlin.features.allGames.db.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeView()
    }

    private fun initializeView() {
        initializeFresco()
        initializeHawk()
       // initializeKoin()
     //  initializeRoom()
    }

    private fun initializeKoin() {
      //  val appModules = listOf(viewModelModule)
      //  startKoin(this, appModules)
    }
    private fun initializeRoom() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

    }

    private fun initializeHawk() {
        Hawk.init(this).build()
    }

    private fun initializeFresco() {
        val config = ImagePipelineConfig.newBuilder(applicationContext)
            .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(applicationContext).build())
            .build()
        Fresco.initialize(this, config)
    }
}