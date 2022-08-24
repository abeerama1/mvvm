package unifedideas.ama.gamekotlin.di

import android.os.Build
import androidx.annotation.RequiresApi
import unifedideas.ama.gamekotlin.features.allGames.AllGamesViewModel
import unifedideas.ama.gamekotlin.features.login.LoginViewModel
import unifedideas.ama.gamekotlin.features.main.MainViewModel
import unifedideas.ama.gamekotlin.features.register.RegisterViewModel
import unifedideas.ama.gamekotlin.features.settings.SettingsViewModel
import unifedideas.ama.gamekotlin.features.splash.SplashViewModel

//@RequiresApi(Build.VERSION_CODES.O)
//val viewModelModule = module {
//    viewModel { SplashViewModel() }
//    viewModel { LoginViewModel() }
//    viewModel { RegisterViewModel() }
//    viewModel { MainViewModel() }
//    viewModel { AllGamesViewModel() }
//    viewModel { SettingsViewModel() }
//
//}