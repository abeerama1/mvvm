package unifedideas.ama.gamekotlin.features.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.features.register.User
import unifedideas.ama.gamekotlin.util.TextChange
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction

class LoginViewModel : BaseViewModel() {

    var userName: MutableLiveData<String> = MutableLiveData<String>()
    var password: MutableLiveData<String> = MutableLiveData<String>()
    var isRememberMe: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }

    var userNameError = MutableLiveData<Int>()
    var passwordError = MutableLiveData<Int>()

    var userNameTextChange: TextChange = object : TextChange {
        override fun onChange(value: String?) {
            userNameError.value = 0
        }
    }
    var passwordTextChange: TextChange = object : TextChange {
        override fun onChange(value: String?) {
            passwordError.value = 0
        }
    }

    init {

    }

    private fun validationUserData(): Boolean {
        return if (TextUtils.isEmpty(userName.value)) {
            userNameError.value = R.string._please_check_your
            false
        } else if (TextUtils.isEmpty(password.value)) {
            passwordError.value = R.string.Please_enter
            false
        } else if (password.value!!.length < 6) {
            passwordError.value = R.string.password_length
            false
        } else {
            passwordError.value = 0
            userNameError.value = 0
            true
        }
    }

    fun onClickLogin() {
        val b1 = validationUserData()
        val b2 = checkCurrentUser()
        if (b1 && b2)
            doAction.value = AppAction.ACTION_LOGIN
    }

    fun onClickRegister() {
        doAction.value = AppAction.ACTION_REGISTER
    }

    private fun checkCurrentUser(): Boolean {
        val user: User? = UserDataUtil.getUser()
        // UserDataUtil.setTotalScore(myDataBase.getUserTotalScore(user.getId()))
        if (userName.value.equals(user!!.userName) && password.value.equals(user.password)) {
            isRememberMe.value?.let { UserDataUtil.saveUser(user, it) }
            return true
        } else {
            showMessageRes.value = (R.string.please_check)
            return false
        }
    }

}