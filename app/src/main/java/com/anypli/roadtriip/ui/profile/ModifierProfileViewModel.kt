package com.anypli.roadtriip.ui.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.data.model.UserBody
import com.anypli.roadtriip.data.model.UserProfile
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.helper.TypeMessage
import com.anypli.roadtriip.global.utilities.isValidEmail
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ModifierProfileViewModel : BaseViewModel() {


    private val userRepository = RepositoriesUtility.getUserRepository()

    private val gson = Gson()


    var userData = MutableStateFlow<User?>(null)

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _lastname = MutableStateFlow("")
    val lastname: StateFlow<String> = _lastname

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _errorMail = MutableStateFlow<Int?>(null)
    val errorMail: StateFlow<Int?> = _errorMail

    private val _errorname = MutableStateFlow<Int?>(null)
    val errorname: StateFlow<Int?> = _errorname

    private val _errorlastname = MutableStateFlow<Int?>(null)
    val errorlastname: StateFlow<Int?> = _errorlastname

    init {
        viewModelScope.launch {
            val user = userRepository.getUser()
            user?.let {
                _name.value = it.Fname ?: ""
                _lastname.value = it.Lname?:""
                _email.value = it.email?:""
                userData.value = user
            }
        }
    }
    fun onEmailChange(value: String) {
        _email.value = value
    }
    fun onNameChange(value: String) {
        _name.value = value
    }
    fun onLastNameChange(value: String) {
        _lastname.value = value
    }
    fun onMiseAJourClicked() {
        clearErrorMessages()
        if (_email.value.isEmpty()) {
            updateErrorEmail(R.string.empty)

        } else if (!isValidEmail(_email.value)) {
            updateErrorEmail(R.string.invalid_email_format)
        }
        if (_name.value.isEmpty()) {
            updateErrorName(R.string.empty_name)
        }
        if (_lastname.value.isEmpty()) {
            updateErrorLastName(R.string.empty_lastname)
        } else {
            viewModelScope.launch {
                showBlockProgressBar()
                try {
                    val user = userRepository.getUser()
                    if (user != null) {
                        val userId = user.id
                        if (userId != null) {
                            val userBody = UserBody(
                                Fname =_name.value,
                                Lname = _lastname.value ,
                                phone_number = user.phone_number ,
                                email = _email.value ,
                                password = null ,

                            )
                            user
                            val result = withContext(Dispatchers.IO) {
                                userRepository.updateUser(userId, userBody)
                            }
                            Log.d("UpdateUser", "Update user result: $result")
                            hideBlockProgressBar()
                            handleGlobalUp(null)
                           // navigate(Navigation.SigninScreen)
                        }
                    }
                } catch (e: Exception) {
                    hideBlockProgressBar()
                    handleGlobalUp(e)
                }
            }
        }
    }
    fun onDeletedClicked () {
        clearErrorMessages ()
        viewModelScope.launch {
            showBlockProgressBar()
            try {
                val user = userRepository.getUser()
                if (user != null) {
                    val userId = user.id
                    withContext(Dispatchers.IO) {
                        if (userId != null) {
                            userRepository.deleteUser(userId)
                        }
                    }
                    Log.d("DeleteUser", "Delete user result: success")
                    hideBlockProgressBar()
                    handleGlobalSup(null)
                }
            } catch (e: Exception) {
                hideBlockProgressBar()
                handleGlobalSup(e)

            }
        }
    }
    fun onDeleteCliked () {
        clearErrorMessages()
        viewModelScope.launch {
            showBlockProgressBar()
            try {
                val user = userRepository.getUser()
                if(user!=null){
                    val userId = user.id
                    withContext(Dispatchers.IO){
                        if (userId!=null){
                            userRepository.deleteUser(userId)
                        }
                    }
                    Log.d("DeleteUser","Delete user result : succes")
                    hideBlockProgressBar()
                    handleGlobalSup(null)
                }
            }catch(e: Exception){
                hideBlockProgressBar()
                handleGlobalSup(e)
            }
        }
    }

    private fun handleGlobalSup(exception:  java.lang.Exception?){
        if (exception == null){
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.user_deleted_success), okActionBlock = {
                navigate(Navigation.SigninScreen)
            })

        }else{
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.global_server_error))
        }



    }
    private fun handleGlobalUp(exception:  java.lang.Exception?){
        if (exception == null){
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.user_updated_success), okActionBlock = {
                navigate(Navigation.SigninScreen)
            })

        }else{
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.global_server_error))
        }



    }
    private fun clearErrorMessages() {
        _errorMail.value = null
        _errorname.value = null
        _errorlastname.value = null
    }
    private fun updateErrorEmail(message: Int) {
        _errorMail.value = message
    }
    private fun updateErrorName(message: Int) {
        if (_name.value.isEmpty()) {
            _errorname.value = message
        }
    }
    private fun updateErrorLastName(message: Int) {
        if (_lastname.value.isEmpty()) {
            _errorlastname.value = message
        }
    }

}

