package com.anypli.roadtriip.ui.cars

import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.TypeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarsViewModel : BaseViewModel() {

    private val carRepository = RepositoriesUtility.getCarRepository()


    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId

    private val _color = MutableStateFlow("")
    val color: StateFlow<String> = _color

    private val _errorname = MutableStateFlow<Int?>(null)
    val errorname: StateFlow<Int?> = _errorname

    private val _errorcolor = MutableStateFlow<Int?>(null)
    val errorcolor: StateFlow<Int?> = _errorcolor

    private val _erroruserId = MutableStateFlow<Int?>(null)
    val erroruserId: StateFlow<Int?> = _erroruserId


    fun onNameChange(value: String) {
        _name.value = value
    }
    fun onColorChange(value: String) {
        _color.value = value
    }
    fun onUserIdChange(value: String) {
        _userId.value = value
    }


    fun onAjouterClicked()  {
        clearErrorMessages()
        if (_name.value.isEmpty() ) {
            updateErrorName(R.string.empty_email)
        }
        else if (_color.value.isEmpty()){
            updateErrorColor(R.string.empty_password)
        }
        else if (_userId.value.isEmpty()) {
            updateErrorUserId(R.string.empty)
        }
        else {
            viewModelScope.launch {
                showBlockProgressBar()
                try {
                    withContext(Dispatchers.IO){
                        carRepository.createCar(
                            name = name.value , color = color.value ,
                            userId = userId.value , carPicture = "")
                    }
                    hideBlockProgressBar()
                    showSimpleDialog(message = TypeMessage.StringMessage("succes"))
                    //navigate(Navigation.HomeScreen)
                } catch (e: Exception) {
                    hideBlockProgressBar()
                    handleGlobalError(e)
                }
            }
        }
    }

    private fun clearErrorMessages() {
        _errorname.value = null
        _errorcolor.value = null
        _erroruserId.value = null
    }
    private fun updateErrorName(message: Int) {
        if (_name.value.isEmpty()) {
            _errorname.value = message
        }
    }

    private fun updateErrorColor(message: Int) {
        if (_color.value.isEmpty()) {
            _errorcolor.value = message
        }
    }

    private fun updateErrorUserId(message: Int) {
        if (_userId.value.isEmpty()) {
            _erroruserId.value = message
        }
    }

}

