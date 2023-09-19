package com.anypli.roadtriip.ui.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.helper.TypeMessage
import com.anypli.roadtriip.global.utilities.PHONE_LENGTH
import com.anypli.roadtriip.global.utilities.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel : BaseViewModel() {

    private val userRepository = RepositoriesUtility.getUserRepository()

    private val _Fname = MutableStateFlow("")
    val Fname: StateFlow<String> = _Fname

    private val _Lname = MutableStateFlow("")
    val Lname: StateFlow<String> = _Lname

    private val _phone = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phone

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordhidden = MutableStateFlow(true)
    val passwordHidden: StateFlow<Boolean> = _passwordhidden

    private val _confirmpassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmpassword

    private val _errorFName = MutableStateFlow<Int?>(null)
    val errorFName: StateFlow<Int?> = _errorFName

    private val _errorLName = MutableStateFlow<Int?>(null)
    val errorLName: StateFlow<Int?> = _errorLName

    private val _errorphone = MutableStateFlow<Int?>(null)
    val errorphone: StateFlow<Int?> = _errorphone

    private val _errorMail = MutableStateFlow<Int?>(null)
    val errorMail: StateFlow<Int?> = _errorMail

    private val _errorPass = MutableStateFlow<Int?>(null)
    val errorPass: StateFlow<Int?> = _errorPass

    private val _errorConfirmPass = MutableStateFlow<Int?>(null)
    val errorConfirmPass: StateFlow<Int?> = _errorConfirmPass

    fun onEmailChange(value: String) {
        _email.value = value
    }
    fun onFNameChange(value: String) {
        _Fname.value = value
    }
    fun onLNameChange(value: String) {
        _Lname.value = value
    }
    fun onPhoneChange(value: String) {
        _phone.value = value
    }
    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun onPasswordHiddenChange(value: Boolean) {
        _passwordhidden.value = value

    }
    fun onConfirmPasswordChange(value: String) {
        _confirmpassword.value = value
    }

    fun onSigninClicked(){
        navigate(Navigation.SigninScreen)
    }
    fun onResetPasswordClicked(){
        navigate(Navigation.ResetPassScreen)
    }
    fun onRegisterClicked()  {
        clearErrorMessages()
        if (_email.value.isEmpty() ) {
            updateErrorMail(R.string.empty_email)
        }
        else if (!isValidEmail(_email.value)) {
            updateErrorMail(R.string.invalid_email_format)
        }
        else if (_password.value.isEmpty()){
            updateErrorpass(R.string.empty_password)
        }
        else if (_Fname.value.isEmpty()) {
            updateErrorName(R.string.empty)
        }
        else if (_Lname.value.isEmpty()) {
            updateErrorName(R.string.empty)
        }
        else if (_phone.value.isEmpty()) {
            updateErrorPhone(R.string.empty)
        }
        else if (_phone.value.length != PHONE_LENGTH) {
            updateValiderFormatPass(R.string.invalid_phone_format)
        }
       /* else if (_confirmpassword.value.isEmpty()) {
            updateErrorConfirmPass(R.string.empty)
        }*/
        else if ( _password.value.isEmpty()) {
            updateValiderConfirmPass(R.string.pass_not_match)
        }
        else {
            viewModelScope.launch {
                showBlockProgressBar()
                try {
                    val result = withContext(Dispatchers.IO){
                        userRepository.createUser(firstName = Fname.value , lastName = Lname.value, phoneNumber = _phone.value , email=_email.value ,password= _password.value, profilePicture = "")
                    }
                    Log.d("Signup", "Signup result: $result")
                    hideBlockProgressBar()
                    //showSimpleDialog(message = TypeMessage.StringMessage("succes"))
                    navigate(Navigation.SigninScreen)

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
        _errorFName.value = null
        _errorLName.value = null
        _errorphone.value = null
        _errorConfirmPass.value = null
    }
    private fun updateErrorMail(message: Int) {
        _errorMail.value = message
    }
    private fun updateErrorpass(message: Int) {
        _errorPass.value = message
    }
    private fun updateErrorName(message: Int) {
        if (_Fname.value.isEmpty()) {
            _errorFName.value = message
        }
    }
    private fun updateErrorLName(message: Int) {
        if (_Lname.value.isEmpty()) {
            _errorLName.value = message
        }
    }
    private fun updateErrorPhone(message: Int) {
        if (_phone.value.isEmpty()) {
            _errorphone.value = message
        }
    }
    private fun updateErrorConfirmPass(message: Int) {
        if (_confirmpassword.value.isEmpty()) {
            _errorConfirmPass.value = message
        }
    }
    private fun updateValiderConfirmPass(message: Int) {
        if (_password.value != _confirmpassword.value) {
            _errorConfirmPass.value = message
        }
    }
    private fun updateValiderFormatPass(message: Int) {
        if (_phone.value.length != PHONE_LENGTH) {
            _errorphone.value = message
        }

    }
}

