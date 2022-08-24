package unifedideas.ama.gamekotlin.features.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseActivity
import unifedideas.ama.gamekotlin.databinding.ActivityLoginBinding
import unifedideas.ama.gamekotlin.features.main.MainActivity
import unifedideas.ama.gamekotlin.features.register.RegisterActivity
import unifedideas.ama.gamekotlin.features.splash.SplashViewModel
import unifedideas.ama.gamekotlin.util.eventBus.AppAction

class LoginActivity : BaseActivity() {

  //  private val viewModel: LoginViewModel by inject()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initializeView()
    }

    private fun initializeView() {

        viewModel.showMessageRes.observe(this, Observer { message ->
            showToast(message)
        })
        viewModel.doAction.observe(this, Observer { action ->
            when (action) {
                AppAction.ACTION_LOGIN -> startNewActivity(MainActivity::class.java)
                AppAction.ACTION_REGISTER -> startNewActivity(RegisterActivity::class.java)
            }
        })
    }
}