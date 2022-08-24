package unifedideas.ama.gamekotlin.features.splash


import android.content.Intent.ACTION_MAIN
import android.os.Handler
import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction

class SplashViewModel : BaseViewModel() {
    private val SPLASH_TIME: Int = 2000
    private val handler: Handler = Handler()

    private val mRunnable = Runnable {
        if (UserDataUtil.isRememberMeChecked()) {
            doAction.value = AppAction.ACTION_MAIN
        } else {
            doAction.value = AppAction.ACTION_LOGIN
        }
    }

    fun onStart() {
        handler.postDelayed(mRunnable, SPLASH_TIME.toLong())
    }

    fun onDestroy() {
        handler.removeCallbacks(mRunnable)
    }
}