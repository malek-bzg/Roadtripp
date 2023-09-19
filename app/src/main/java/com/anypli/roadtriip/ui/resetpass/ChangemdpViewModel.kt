package com.anypli.roadtriip.ui.resetpass

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.data.model.PasswordUpdateBody
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.helper.TypeMessage
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChangemdpViewModel: BaseViewModel() {

    private val userRepository = RepositoriesUtility.getUserRepository()

    private val gson = Gson()

    private val _ancienPass = MutableStateFlow("")
    val ancienPass: StateFlow<String> = _ancienPass

    private val _newPass = MutableStateFlow("")
    val newPass: StateFlow<String> = _newPass

    private val _confirmPass = MutableStateFlow("")
    val confirmPass: StateFlow<String> = _confirmPass


    private val _errorpass = MutableStateFlow<Int?>(null)
    val errorpass: StateFlow<Int?> = _errorpass

    private val _errornewPass = MutableStateFlow<Int?>(null)
    val errornewPass: StateFlow<Int?> = _errornewPass

    private val _errorconfirmPass = MutableStateFlow<Int?>(null)
    val errorconfirmPass: StateFlow<Int?> = _errorconfirmPass


    fun onPassChange(value: String) {
        _ancienPass.value = value
    }

    fun onNewPassChange(value: String) {
        _newPass.value = value
    }

    fun onconfirmPassChange(value: String) {
        _confirmPass.value = value
    }

    fun onPassResetClicked() {
        clearErrorMessages()

        if (_ancienPass.value.isEmpty()) {
            updateErrorAncienPass(R.string.empty)
        }

        if (_newPass.value.isEmpty()) {
            updateErrorNewPass(R.string.empty)
        }

        if (_confirmPass.value.isEmpty()) {
            updateErrorConfirmPass(R.string.empty)
        }
        else if ( _newPass.value != _confirmPass.value) {
            updateValiderConfirmPass(R.string.pass_not_match)
        } else{
            viewModelScope.launch {
                showBlockProgressBar()
                try {
                    val user = userRepository.getUser()
                    if (user != null) {
                        val userId = user.id
                        if (userId != null) {
//                            if (!_ancienPass.value.isEmpty() && !_newPass.value.isEmpty() && !_confirmPass.value.isEmpty()) {
                                val result = withContext(Dispatchers.IO) {
                                    userRepository.updatePassword(
                                        userId , PasswordUpdateBody(
                                            currentPassword = _ancienPass.value,
                                            newPassword  = _newPass.value
                                        )
                                    )
                                }
                                Log.d("UpdateUser", "Update user result: $result")
                                hideBlockProgressBar()
                                handleGlobalPass(null)

                               //navigate(Navigation.ProfileScreen)


                        }
                    }
                } catch (e: Exception) {
                    hideBlockProgressBar()
                    handleGlobalPass(e)
                }
            }
        }
    }
    private fun handleGlobalPass(exception:  java.lang.Exception?){
        if (exception == null){
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.password_updated_success), okActionBlock = {
                navigate(Navigation.SigninScreen)
            })

        }else{
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.global_server_error))
        }



    }

    private fun clearErrorMessages() {
        _errorpass.value = null
        _errornewPass.value = null
        _errorconfirmPass.value = null
    }

    private fun updateErrorAncienPass(message: Int) {
        if (_ancienPass.value.isEmpty()) {
            _errorpass.value = message
        }
    }

    private fun updateErrorNewPass(message: Int) {
        if (_newPass.value.isEmpty()) {
            _errornewPass.value = message
        }
    }

    private fun updateErrorConfirmPass(message: Int) {
        if (_confirmPass.value.isEmpty()) {
            _errorconfirmPass.value = message
        }


    }

    private fun updateValiderConfirmPass(message: Int) {
        if (_newPass.value != _confirmPass.value) {
            _errorconfirmPass.value = message
        }


    }
}