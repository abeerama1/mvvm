package unifedideas.ama.gamekotlin.features.main

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuBuilder
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseActivity
import unifedideas.ama.gamekotlin.databinding.ActivityMainBinding
import unifedideas.ama.gamekotlin.features.allGames.db.AppDatabase
import unifedideas.ama.gamekotlin.features.login.LoginActivity
import unifedideas.ama.gamekotlin.features.login.LoginViewModel
import unifedideas.ama.gamekotlin.features.settings.SettingsActivity
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction
import java.util.concurrent.Executors
import java.util.logging.Logger


class MainActivity : BaseActivity() {

    // private val viewModel: MainViewModel by inject()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initializeView()
    }

    @SuppressLint("NewApi")
    private fun initializeView() {

        viewModel.onStart(application)

        viewModel.showMessageRes.observe(this, Observer { msg ->
            showToast(msg)
        })

        viewModel.doAction.observe(this, Observer { action ->
            when (action) {
                AppAction.ACTION_CHECK_NUMBER_GAME -> checkGame()
                AppAction.ACTION_NEW_GAME -> viewModel.startNewGame()
                AppAction.ACTION_LOGIN -> startNewActivity(LoginActivity::class.java)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkGame() {
        if (viewModel.validationGameNumber()) {
            if (viewModel.checkHiddenNumber()) {
                viewModel.gameSuccess()
                viewModel.showCustomToast(
                    R.string.correct,
                    R.drawable.correct,
                    R.color.greenColor,
                    R.raw.win, this
                )
            } else {
                viewModel.generateGame(0)
                viewModel.showCustomToast(
                    R.string.wrong,
                    R.drawable.wrong,
                    R.color.redColor,
                    R.raw.wrong, this
                )
            }


//            AsyncTask.execute {
//                viewModel.game?.let { AppDatabase.getInstance(this).gamesDao().saveNewGame(it) }
//            }

//            Executors.newSingleThreadExecutor().execute {
//                viewModel.game?.let { AppDatabase.getInstance(this).gamesDao().saveNewGame(it) }
//            }

            viewModel.game?.let { viewModel.insert(it) }
            viewModel.gameNumber.value = ""
            viewModel.startNewGame()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        (menu as MenuBuilder).setOptionalIconsVisible(true)
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> startNewActivity(SettingsActivity::class.java)
            R.id.menu_log_out -> viewModel.logoutUser()
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        viewModel.totalScore.value = (UserDataUtil.getTotalScore()).toString()
    }

}