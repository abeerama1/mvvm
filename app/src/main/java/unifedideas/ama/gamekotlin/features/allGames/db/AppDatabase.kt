package unifedideas.ama.gamekotlin.features.allGames.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game
import unifedideas.ama.gamekotlin.features.register.User

@Database(entities = [Game::class , User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

   abstract fun gamesDao(): GamesDatabaseDao
   //abstract fun d(): dao

    companion object {
        private const val DB_NAME = "UsersDatabase"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DB_NAME
            ).fallbackToDestructiveMigration().build()

    }




}