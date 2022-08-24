package unifedideas.ama.gamekotlin.features.allGames.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game
import unifedideas.ama.gamekotlin.features.register.User


class Repository(application: Application?) {

    private var gamesDatabaseDao: GamesDatabaseDao? = null
    private var games: LiveData<List<Game>>? = null

    init {
        val db: AppDatabase = application?.let { AppDatabase.getInstance(it) }!!
        gamesDatabaseDao = db.gamesDao()
        games = gamesDatabaseDao!!.getAllGames()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return games!!
    }

    fun insert(game: Game) {
        gamesDatabaseDao?.let { insertAsyncTask(it).execute(game) }
    }

    private class insertAsyncTask constructor(dao: GamesDatabaseDao) :
        AsyncTask<Game?, Void?, Void?>() {
        private val mAsyncTaskDao: GamesDatabaseDao = dao
        override fun doInBackground(vararg params: Game?): Void? {
            mAsyncTaskDao.saveNewGame(params[0]!!)
            return null
        }
    }


    fun insertUser(user: User) {
        gamesDatabaseDao?.let { insertUserAsyncTask(it).execute(user) }
    }

    private class insertUserAsyncTask constructor(dao: GamesDatabaseDao) :
        AsyncTask<User?, Void?, Void?>() {
        private val mAsyncTaskDao: GamesDatabaseDao = dao
        override fun doInBackground(vararg params: User?): Void? {
            mAsyncTaskDao.saveUser(params[0]!!)
            return null
        }
    }
}