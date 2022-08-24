package unifedideas.ama.gamekotlin.features.register

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game
import unifedideas.ama.gamekotlin.features.allGames.db.Repository
import unifedideas.ama.gamekotlin.util.TextChange
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction
import unifedideas.ama.kotlinapp.util.AppUtil
import java.text.SimpleDateFormat
import java.util.*


class RegisterViewModel : BaseViewModel() {

    var countriesList = ArrayList<String>()
    private var simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyy", Locale.US)
    private lateinit var myCalendar: Calendar

    lateinit var user: User

    val fullName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val userName: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()
    var country: MutableLiveData<String> = MutableLiveData()
    val dateOfBirth: MutableLiveData<String> = MutableLiveData()
    val imageUri: MutableLiveData<String> = MutableLiveData()
    val gender: MutableLiveData<String> = MutableLiveData()

    var selectedItem: Any? = null
    val fullNameError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val emailError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val userNameError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val passwordError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val confirmPasswordError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val countryError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val dateOfBirthError: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }

    private var mRepository: Repository? = null

    private lateinit var selectedCountry: String


    val clicksListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectedCountry = parent!!.getItemAtPosition(position) as String
            Log.d("TAG", "onItemSelected: " + selectedCountry)
        }
    }

    var fullNameTextChange: TextChange = object : TextChange {
        override fun onChange(value: String?) {
            fullNameError.value = 0
        }
    }
    var emailTextChange: TextChange = object : TextChange {
        override fun onChange(value: String?) {
            emailError.value = 0
        }
    }
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
    var confirmPasswordTextChange: TextChange = object : TextChange {
        override fun onChange(value: String?) {
            confirmPasswordError.value = 0
        }
    }

    var dateOfBirthTextChange: TextChange = object : TextChange {
        override fun onChange(value: String?) {
            dateOfBirthError.value = 0
        }
    }

    init {
        user = User()
    }

    fun onGenderChanged(radioGroup: RadioGroup?, id: Int) {
        when (radioGroup!!.checkedRadioButtonId) {
            R.id.reg_male_rb -> gender.value = "Male"
            R.id.reg_female_rb -> gender.value = "Female"
        }
        Log.d("ABR", "onSplitTypeChanged: " + radioGroup!!.checkedRadioButtonId)
        Log.d("ABR", "onSplitTypeChanged: " + gender.value)
    }


    fun onStart(selectedItem: Any) {
        this.selectedItem = selectedItem

        user.country = selectedItem.toString()
        Log.d("ABR", "onStart: " + country.value)
    }

    fun onCreate(application: Application) {
        mRepository = Repository(application)
    }

    fun insert(user: User) {
        mRepository!!.insertUser(user)
    }

    fun onClickSave() {
        val b1 = validationFullName()
        val b2 = validationEmail()
        val b3 = validationPassword()
        val b4 = validationCountry()
        val b5 = validationDateOfBirth()
        val b6 = validationUserName()

        if (b1 && b2 && b3 && b4 && b5 && b6)
            doAction.value = AppAction.ACTION_SAVE
    }

    private fun validationFullName(): Boolean {
        return if (TextUtils.isEmpty(fullName.value)) {
            fullNameError.value = R.string.invalid_full_name
            false
        } else {
            fullNameError.value = 0
            true
        }
    }

    private fun validationUserName(): Boolean {
        return if (TextUtils.isEmpty(userName.value)) {
            userNameError.value = R.string.invalid_full_name
            false
        } else {
            userNameError.value = 0
            true
        }
    }

    private fun validationEmail(): Boolean {
        return if (TextUtils.isEmpty(email.value)) {
            emailError.value = R.string.invalid_email
            false
        } else if (!AppUtil.isEmailValid(email.value)) {
            emailError.value = R.string.invalid_email2
            false
        } else {
            emailError.value = 0
            true
        }
    }


    private fun validationPassword(): Boolean {
        return if (TextUtils.isEmpty(password.value)) {
            passwordError.value = R.string.invalid_name
            false
        } else if (password.value!!.length < 6) {
            passwordError.value = R.string.invalid_password_length
            false
        } else if (TextUtils.isEmpty(confirmPassword.value)) {
            confirmPasswordError.value = R.string.invalid_confirm_password
            false
        } else if (!(confirmPassword.value).equals(password.value)) {
            confirmPasswordError.value = R.string.password_should_be_same
            false
        } else {
            passwordError.value = 0
            confirmPasswordError.value = 0
            true
        }
    }

    private fun validationCountry(): Boolean {
        return if (TextUtils.isEmpty(selectedCountry)) {
            countryError.value = (R.string.invalid_country)
            false
        } else {
            countryError.value = 0
            true
        }
    }

    private fun validationDateOfBirth(): Boolean {
        return if (TextUtils.isEmpty(dateOfBirth.value)) {
            dateOfBirthError.value = R.string.invalid_birthday
            false
        } else {
            dateOfBirthError.value = 0
            true
        }
    }

    fun setData() {
        countriesList.add("Palestine")
        countriesList.add("Jordan")
        countriesList.add("Syria")
        countriesList.add("UK")
        countriesList.add("USA")
    }

    fun showCalendar(context: Context) {
        myCalendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateOfBirth.value = simpleDateFormat.format(myCalendar.getTime())
            }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        ).show()
    }


    private fun generateUser() {
        user = User()
        user.fullName = (fullName.value)
        user.email = (email.value)
        user.userName = (userName.value)
        user.birthDate = (simpleDateFormat.format(myCalendar.getTime()))
        user.password = (password.value)
        user.image = (imageUri.value)
        user.gender = gender.value
        user.country = selectedCountry
    }

    fun onClickDate() {
        doAction.value = AppAction.ACTION_SHOW_DATE_PICKER
    }

    fun saveRegisterData() {
        generateUser()
        UserDataUtil.saveUser(user, false)
        Log.d("ABR", "saveRegisterData: " + user.id)

    }
}