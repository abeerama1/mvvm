package unifedideas.ama.gamekotlin.features.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.MainScope
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseActivity
import unifedideas.ama.gamekotlin.databinding.ActivitySplashBinding
import unifedideas.ama.gamekotlin.features.login.LoginActivity
import unifedideas.ama.gamekotlin.features.main.MainActivity
import unifedideas.ama.gamekotlin.util.eventBus.AppAction

class SplashActivity : BaseActivity() {

    // private val viewModel: SplashViewModel by inject()
    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        val factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.onStart()
        binding.lifecycleOwner = this
        initializeView();
    }

    private fun initializeView() {
        viewModel.doAction.observe(this, Observer { action ->
            when (action) {
                AppAction.ACTION_LOGIN -> startNewActivity(LoginActivity::class.java)
                AppAction.ACTION_MAIN -> startNewActivity(MainActivity::class.java)
            }
        })

    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}