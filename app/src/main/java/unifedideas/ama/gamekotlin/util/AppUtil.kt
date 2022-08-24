package unifedideas.ama.kotlinapp.util

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.inputmethod.InputMethodManager


object AppUtil {


        fun convertDpToPixel(context: Context, dp: Float): Int {
            val resources = context.resources
            val dm = resources.displayMetrics
            val px = dp * (dm.densityDpi / 160f)
            return px.toInt()
        }

        fun isEmailValid(email: CharSequence?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }


    fun String.isValidEmail() =  android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

        fun hideKeyboard(activity: Activity) {
            try {
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            } catch (e: Exception) {
            }
        }


}