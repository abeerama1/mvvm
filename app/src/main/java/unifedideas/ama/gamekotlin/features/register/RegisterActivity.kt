package unifedideas.ama.gamekotlin.features.register

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseActivity
import unifedideas.ama.gamekotlin.databinding.ActivityRegisterBinding
import unifedideas.ama.gamekotlin.features.login.LoginActivity
import unifedideas.ama.gamekotlin.features.settings.SettingsViewModel
import unifedideas.ama.gamekotlin.util.eventBus.AppAction

class RegisterActivity : BaseActivity() {

   // private val viewModel: RegisterViewModel by inject()
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initializeView()
    }

    private fun initializeView() {
        viewModel.onCreate(applicationContext as Application)
        viewModel.doAction.observe(this, Observer { action ->
            when (action) {
                AppAction.ACTION_SAVE -> {
                    viewModel.saveRegisterData()
                    startNewActivity(LoginActivity::class.java)
                }
                AppAction.ACTION_SHOW_DATE_PICKER -> viewModel.showCalendar( this)
            }
        })

        initializeSpinner(binding.regCountrySp)
         viewModel.onStart(binding.regCountrySp.selectedItem)
        Log.d("ABR", "initializeView: " + binding.regCountrySp.selectedItem)

        viewModel.insert(viewModel.user)
    }

    private fun initializeSpinner(spinner: Spinner) {
        viewModel.setData()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.item_spinner, viewModel.countriesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}