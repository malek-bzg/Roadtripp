package com.anypli.roadtriip.ui.resetpass

import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResetPassViewModel : BaseViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _errorMail = MutableStateFlow<Int?>(null)
    val errorMail: StateFlow<Int?> = _errorMail


    fun onEmailChange(value: String) {
        _email.value = value

    }

    fun onSignupClicked(){
        navigate(Navigation.SignupScreen)
    }
    fun onResetClicked()  {
        clearErrorMessages()


        if (_email.value.isEmpty()) {
            updateErrorMessage(R.string.empty_email)
        }  else if (!isValidEmail(_email.value)) {
            updateErrorMail(R.string.invalid_email_format)
        }


    }
    private fun clearErrorMessages() {
        _errorMail.value = null
    }

    private fun updateErrorMail(message: Int) {
        _errorMail.value = message
    }
    private fun updateErrorMessage(message: Int) {
        _errorMail.value = message

    }


}