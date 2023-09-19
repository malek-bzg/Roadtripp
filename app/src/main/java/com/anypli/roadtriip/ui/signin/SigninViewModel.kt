import android.content.Context
import android.support.v4.os.IResultReceiver._Parcel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.data.retrofit.UserSession
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.isValidEmail
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SigninViewModel : BaseViewModel() {

    private val userRepository = RepositoriesUtility.getUserRepository()

    private val gson = Gson()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _passwordhidden = MutableStateFlow(true)
    val passwordHidden: StateFlow<Boolean> = _passwordhidden

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _errorMail = MutableStateFlow<Int?>(null)
    val errorMail: StateFlow<Int?> = _errorMail

    private val _errorPass = MutableStateFlow<Int?>(null)
    val errorPass: StateFlow<Int?> = _errorPass


    fun onEmailChange(value: String) {
        _email.value = value
    }
    fun onPasswordChange(value: String) {
        _password.value = value
    }
    fun onPasswordHiddenChange(value: Boolean) {
        _passwordhidden.value = value
    }
    fun onResetPasswordClicked() {
        navigate(Navigation.ResetPassScreen)
    }
    fun onCreateAccountClicked() {
        navigate(Navigation.SignupScreen)
    }
    fun onLoginClicked(context: Context) {
        clearErrorMessages()
        if (_email.value.isEmpty()) {
            updateErrorMail(R.string.empty_email)
        } else if (!isValidEmail(_email.value)) {
            updateErrorMail(R.string.invalid_email_format)
        } else if (_password.value.isEmpty()) {
            updateErrorpass(R.string.empty_password)
        } else {
            viewModelScope.launch {
                showBlockProgressBar()
                try {
                    val result = withContext(Dispatchers.IO) {
                        userRepository.login(email = _email.value, password = _password.value)
                    }
                    Log.d("Login", "Login result: $result")
                    if (result != null) {
                        val userJson = gson.toJson(result.user)
                        userRepository.saveUser(result.user)
                    }
                    hideBlockProgressBar()
                    Log.d(" success", " success")
                    navigate(Navigation.ProfileScreen)
                    Log.d("navigate success", "navigate success")
                    } catch (e: Exception) {
                    hideBlockProgressBar()
                    handleGlobalError(e)
                }
            }
        }
    }
    private fun clearErrorMessages() {
        _errorMail.value = null
        _errorPass.value = null
    }
    private fun updateErrorMail(message: Int) {
        _errorMail.value = message
    }
    private fun updateErrorpass(message: Int) {
        _errorPass.value = message
    }
}
