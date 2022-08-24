package unifedideas.ama.gamekotlin.features.settings

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseActivity
import unifedideas.ama.gamekotlin.databinding.ActivitySettingsBinding
import unifedideas.ama.gamekotlin.features.allGames.AllGamesActivity
import unifedideas.ama.gamekotlin.features.allGames.db.AppDatabase
import unifedideas.ama.gamekotlin.features.main.MainViewModel
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction
import java.util.concurrent.Executors

class SettingsActivity : BaseActivity() {

    //  private val viewModel: SettingsViewModel by inject()
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var viewModel: SettingsViewModel
    private var date: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        val factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProvider(this, factory).get(SettingsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initializeView()
    }

    private fun initializeView() {
        viewModel.doAction.observe(this, Observer { action ->
            when (action) {
                AppAction.ACTION_SHOW_ALL_GAMES -> startNewActivity(AllGamesActivity::class.java)
                AppAction.ACTION_SHOW_LAST_GAME_DATE -> {
                    Executors.newSingleThreadExecutor().execute {
                        date = AppDatabase.getInstance(this).gamesDao()
                            .getLastDate(UserDataUtil.getUserId())
                        Log.d("ABR", "initializeView: " + UserDataUtil.getUserId())
                    }
                    Toast.makeText(this, "Last Date:  $date", Toast.LENGTH_SHORT).show()

                }
            }
            // AppAction.ACTION_CHANGE_PASSWORD ->
            //AppAction.ACTION_CLEAR_GAME_HISTORY ->

        })
    }


}