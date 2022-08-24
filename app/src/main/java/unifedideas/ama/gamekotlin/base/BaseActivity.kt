package unifedideas.ama.gamekotlin.base

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import unifedideas.ama.kotlinapp.util.AppUtil
import unifedideas.ama.kotlinapp.util.eventBus.ActionEvent

open class BaseActivity : AppCompatActivity() {

   internal fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    internal fun showToast(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    open fun startNewActivity(newActivity: Class<*>?) {
        startNewActivity(newActivity, null, false)
    }

    open fun startNewActivity(newActivity: Class<*>?, extras: Intent?) {
        startNewActivity(newActivity, extras, false)
    }

    open fun startNewActivity(newActivity: Class<*>?, extras: Intent?, clearStack: Boolean) {
        val intent = Intent(this, newActivity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        if (extras != null) {
            intent.putExtras(extras)
        }
        AppUtil.hideKeyboard(this)
        startActivity(intent)
    }

//    override fun onStart() {
//        super.onStart()
//        EventBus.getDefault().register(this)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        EventBus.getDefault().unregister(this)
//    }
//
//    @Subscribe
//    fun onActionEvent(actionEvent: ActionEvent) {
////        if (actionEvent.actions == AppAction.ACTION_MAIN) {
////            startNewActivity(MainActivity::class.java)
////        }
//    }

}