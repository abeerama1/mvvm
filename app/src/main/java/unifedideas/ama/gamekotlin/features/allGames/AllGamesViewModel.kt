package unifedideas.ama.gamekotlin.features.allGames

import android.app.Application
import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game
import unifedideas.ama.gamekotlin.features.allGames.adapter.GamesAdapter
import unifedideas.ama.gamekotlin.features.allGames.db.AppDatabase
import unifedideas.ama.gamekotlin.features.allGames.db.GamesDatabaseDao
import unifedideas.ama.gamekotlin.features.allGames.db.Repository
import unifedideas.ama.gamekotlin.util.UserDataUtil
import java.util.*


class AllGamesViewModel() : BaseViewModel() {

    var gamesAdapter: MutableLiveData<GamesAdapter> = MutableLiveData()
    //var games: ArrayList<Game> = ArrayList()

    private var mRepository: Repository? = null
    private var mAllGames: LiveData<List<Game>>? = null

    private lateinit var gamesDao: GamesDatabaseDao

    init {
        isLoading.value = false
        gamesAdapter.value = GamesAdapter()

        isEmpty.value = true
        emptyIcon.value = R.drawable.logo
        emptyText.value = R.string.no

    }

    fun onStart(application: Application) {
        mRepository = Repository(application)
        mAllGames = mRepository!!.getAllGames()
        Log.d("ABR", "onStart: " + (mAllGames!!.value?.size))
    }

    var mOnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        val handler = Handler()
        handler.postDelayed({
            isLoading.value = true
            isRefreshing.value = false
            isEmpty.setValue(mAllGames!!.value!!.isEmpty())
        }, 2000)
    }

    fun getAllWords(): LiveData<List<Game>> {
        return mAllGames!!
    }



}