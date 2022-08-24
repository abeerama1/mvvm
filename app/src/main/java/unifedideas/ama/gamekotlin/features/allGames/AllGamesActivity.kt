package unifedideas.ama.gamekotlin.features.allGames

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.item_game.*
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseActivity
import unifedideas.ama.gamekotlin.databinding.ActivityAllGamesBinding
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game


class AllGamesActivity : BaseActivity() {

   // private val viewModel: AllGamesViewModel by inject()
    private lateinit var binding: ActivityAllGamesBinding
    private lateinit var viewModel: AllGamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_games)
        val factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProvider(this, factory).get(AllGamesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initializeView()
    }

    private fun initializeView() {

//        Executors.newSingleThreadExecutor().execute {
//            viewModel.loadData(applicationContext as Application)
//        }
//        viewModel.isEmpty.value = viewModel.games.isEmpty()

        viewModel.onStart(applicationContext as Application)
        viewModel.getAllWords().observe(this, Observer { games ->
            if (games.isNotEmpty()){
                viewModel.isEmpty.value = false
                viewModel.gamesAdapter.value!!.setGames(games)
            }
            Log.d("ABR", "initializeView: "+ games.size)
        })


    }



}