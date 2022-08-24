package unifedideas.ama.gamekotlin.util

import com.orhanobut.hawk.Hawk
import unifedideas.ama.gamekotlin.features.register.User

class UserDataUtil {

    companion object {
        private val KEY_REMEBER_ME = "isLoggedIn"
        private val KEY_ID = "id"
        private val KEY_FULL_NAME = "fullName"
        private val KEY_EMAIL = "email"
        private val KEY_NAME = "name"
        private val KEY_PASSWORD = "password"
        private val KEY_DATE = "date"
        private val KEY_IMAGE = "image"
        private val KEY_GENDER = "gender"
        private val KEY_COUNTRY = "country"
        private val KEY_TOTAL_SCORE = "total_score"


        fun saveUser(user: User, rememberMe: Boolean) {
            Hawk.put(KEY_REMEBER_ME, rememberMe)
            Hawk.put(KEY_ID, user.id)
            Hawk.put(KEY_FULL_NAME, user.fullName)
            Hawk.put(KEY_EMAIL, user.email)
            Hawk.put(KEY_NAME, user.userName)
            Hawk.put(KEY_PASSWORD, user.password)
            Hawk.put(KEY_DATE, user.birthDate)
            Hawk.put(KEY_IMAGE, java.lang.String.valueOf(user.image))
            Hawk.put(KEY_GENDER, user.gender)
            Hawk.put(KEY_COUNTRY, user.country)
        }

        fun getUser(): User? {
            val user = User()
            user.id = (Hawk.get(KEY_ID, 0))
            user.fullName = (Hawk.get(KEY_FULL_NAME, ""))
            user.email = (Hawk.get(KEY_EMAIL, ""))
            user.userName = (Hawk.get(KEY_NAME, ""))
            user.password = (Hawk.get(KEY_PASSWORD, ""))
            user.birthDate = (Hawk.get(KEY_DATE, ""))
            user.image = ((Hawk.get(KEY_IMAGE, "")))
            user.gender = (Hawk.get(KEY_GENDER, ""))
            user.country = (Hawk.get(KEY_COUNTRY, ""))
            return user
        }

        fun setTotalScore(totalScore: Int) {
            Hawk.put(KEY_TOTAL_SCORE, totalScore)
        }


        fun getTotalScore(): Int {
            return Hawk.get(KEY_TOTAL_SCORE, 0)
        }

        fun getFullName(): String? {
            return Hawk.get(KEY_FULL_NAME, "")
        }

        fun getUserId(): Int {
            return Hawk.get(KEY_ID, 0)
        }

        fun getPassword(): String? {
            return Hawk.get(KEY_PASSWORD, "")
        }

        fun isRememberMeChecked(): Boolean {
            return Hawk.get(KEY_REMEBER_ME, false)
        }

        fun changePassword(newPassword: String?) {
            Hawk.delete(KEY_PASSWORD)
            Hawk.put(KEY_PASSWORD, newPassword)
        }

        fun loggedOut() {
            Hawk.put(KEY_REMEBER_ME, false)
        }
    }
}