package unifedideas.ama.gamekotlin.features.main

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game
import unifedideas.ama.gamekotlin.features.allGames.db.AppDatabase
import unifedideas.ama.gamekotlin.features.allGames.db.Repository
import unifedideas.ama.gamekotlin.features.register.User
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction
import unifedideas.ama.gamekotlin.util.game.Question
import unifedideas.ama.gamekotlin.util.game.Util
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : BaseViewModel() {

    val totalScore: MutableLiveData<String> = MutableLiveData()
    val userName: MutableLiveData<String> = MutableLiveData()
    val userAge: MutableLiveData<String> = MutableLiveData()
    val gameNumber: MutableLiveData<String> = MutableLiveData()

    private var mRepository: Repository? = null

    val gameNumber1: MutableLiveData<String> = MutableLiveData()
    val gameNumber2: MutableLiveData<String> = MutableLiveData()
    val gameNumber3: MutableLiveData<String> = MutableLiveData()
    val gameNumber4: MutableLiveData<String> = MutableLiveData()
    val gameNumber5: MutableLiveData<String> = MutableLiveData<String>().apply { value = "??" }
    val gameNumber6: MutableLiveData<String> = MutableLiveData()
    val gameNumber7: MutableLiveData<String> = MutableLiveData()
    val gameNumber8: MutableLiveData<String> = MutableLiveData()
    val gameNumber9: MutableLiveData<String> = MutableLiveData()

    private lateinit var question: Question;
    private var mediaPlayer: MediaPlayer? = null
    var game: Game? = null

    init {
        getRegisterData()
        startNewGame()
    }

    fun onStart(application: Application) {
        mRepository = Repository(application)
    }


    fun insert(game: Game) {
        mRepository!!.insert(game)
    }

    private fun setGameNumbers() {
        gameNumber1.value = (question.getData()[0][0]) //00
        gameNumber2.value = (question.getData()[0][1]) //01
        gameNumber3.value = (question.getData()[0][2]) //02
        gameNumber4.value = (question.getData()[1][0]) //10
        //gameNumber5.value = (question.getData()[1][1]); //11
        gameNumber6.value = question.getData()[1][2] //12
        gameNumber7.value = (question.getData()[2][0]) //20
        gameNumber8.value = (question.getData()[2][1]) //21
        gameNumber9.value = (question.getData()[2][2]) //22
    }

    fun startNewGame() {
        question = Util.generateQuestion()!!
        Log.d("CRITICAL", "startNewGame: " + question.getHiddenNumber())
        setGameNumbers()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRegisterData() {
        val user: User = UserDataUtil.getUser()!!
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyy")
        val today = LocalDate.now()
        if (user.birthDate!!.isNotEmpty()) {
            val birthday = LocalDate.parse(user.birthDate, formatter)
            val p = Period.between(birthday, today)
            userName.value = user.userName
            userAge.value = (p.years.toString())
        }
    }

    fun generateGame(score: Int) {
        game = Game()
        game!!.userId = (UserDataUtil.getUserId())
        game!!.score = (score)
        val date = Calendar.getInstance().time
        game!!.time = (SimpleDateFormat("H:m", Locale.US).format(date))
        game!!.date = (SimpleDateFormat("MM/dd/yyyy", Locale.US).format(date))
    }

    fun checkHiddenNumber(): Boolean {
        return gameNumber.value!!.toInt() == question.getHiddenNumber()
    }

    fun onClickCheck() {
        doAction.value = AppAction.ACTION_CHECK_NUMBER_GAME
    }

    fun validationGameNumber(): Boolean {
        if (gameNumber.value == null) {
            showMessageRes.value = R.string.please_enter_number_to_paly
            return false
        }
        return true
    }

    fun gameSuccess() {
        generateGame(10)
        val newTotal: Int = UserDataUtil.getTotalScore() + 10
        UserDataUtil.setTotalScore(newTotal)
        totalScore.value = (newTotal.toString())
        Log.d("TAG", "checkGame: " + totalScore.value)
    }

    fun onClickNewGame() {
        doAction.value = AppAction.ACTION_NEW_GAME
    }

    fun logoutUser() {
        UserDataUtil.loggedOut()
        doAction.value = AppAction.ACTION_LOGIN
    }

    @SuppressLint("InflateParams")
    fun showCustomToast(
        stringRes: Int,
        imageRes: Int,
        colorRes: Int,
        ringRes: Int,
        context: Context
    ) {
        val v: View = LayoutInflater.from(context).inflate(R.layout.custom_toast, null, false)
        val layout = v.findViewById<LinearLayout>(R.id.toast_layout_root)
        val image = v.findViewById<ImageView>(R.id.custom_image)
        val text = v.findViewById<TextView>(R.id.custom_text)
        image.setImageResource(imageRes)
        text.setText(stringRes)
        layout.setBackgroundResource(colorRes)
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.view = v
        mediaPlayer = MediaPlayer.create(context, ringRes)
        mediaPlayer!!.start()
        toast.show()
    }

}
