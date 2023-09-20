package com.anypli.roadtriip.ui.event

import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.helper.TypeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEventsViewModel : BaseViewModel() {
    private val eventRepository = RepositoriesUtility.getEventRepository()


    private val _titre = MutableStateFlow("")
    val titre: StateFlow<String> = _titre

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _prix = MutableStateFlow("")
    val prix: StateFlow<String> = _prix

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> = _date

    private val _nb = MutableStateFlow("")
    val nb: StateFlow<String> = _nb

    private val _errortitre = MutableStateFlow<Int?>(null)
    val errortitre: StateFlow<Int?> = _errortitre

    private val _errordescription = MutableStateFlow<Int?>(null)
    val errordescription: StateFlow<Int?> = _errordescription

    private val _errorprix = MutableStateFlow<Int?>(null)
    val errorprix: StateFlow<Int?> = _errorprix

    private val _errornb = MutableStateFlow<Int?>(null)
    val errornb: StateFlow<Int?> = _errornb

    private val _errordate = MutableStateFlow<Int?>(null)
    val errordate: StateFlow<Int?> = _errordate


    fun onTitreChange(value: String) {
        _titre.value = value
    }
    fun onDescriptionChange(value: String) {
        _description.value = value
    }
    fun onPrixChange(value: String) {
        _prix.value = value
    }
    fun onDateChange(value: String) {
        _date.value = value
    }
    fun onImageChange(value: String) {
        _prix.value = value
    }
    fun onNbDispoChange(value: String) {
        _nb.value = value
    }
    fun onCameraScreenClicked(){
        navigate(Navigation.CameraScreen)
    }
    fun onAjouterClicked()  {
        clearErrorMessages()

        if (_titre.value.isEmpty()) {
            updateErrorTitre(R.string.empty)
        }

        if (_description.value.isEmpty()) {
            updateErrorDescription(R.string.empty)
        }

        if (_prix.value.isEmpty()) {
            updateErrorPrix(R.string.empty)
        }

        if (_nb.value.isEmpty()) {
            updateErrorNb(R.string.empty)
        }

        if (_date.value.isEmpty()) {
            updateErrorDate(R.string.empty)

        } /*else if (!isValidDateFormat(_date.value)) {
            updateErrorDate(R.string.invalid_date_format)
        }*/ else {
            viewModelScope.launch {
                showBlockProgressBar()
                try {
                    val result = withContext(Dispatchers.IO){
                        eventRepository.createEvent(
                            nameDestination = _titre.value , Dateofdeparture = _date.value, prix = _prix.value , description =_description.value ,
                            maximumNumberOfplaces = _nb.value, eventPicture = "")
                    }
                    hideBlockProgressBar()
                    //showSimpleDialog(message = TypeMessage.StringMessage("succes"))
                    //navigate(Navigation.CameraScreen)
                    handleGlobalUpEvent(null)

                } catch (e: Exception) {
                    hideBlockProgressBar()
                    handleGlobalUpEvent(e)
                }

            }

        }
    }
    private fun handleGlobalUpEvent(exception:  java.lang.Exception?){
        if (exception == null){
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.event_creer_avec_succes), okActionBlock = {
                navigate(Navigation.HomeScreen)
            })

        }else{
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.global_server_error))
        }
    }
    private fun clearErrorMessages() {
        _errortitre.value = null
        _errordescription.value = null
        _errorprix.value = null
        _errornb.value = null
        _errordate.value = null
    }
    private fun updateErrorTitre(message: Int) {
        if (_titre.value.isEmpty()) {
            _errortitre.value = message
        }
    }
    private fun updateErrorDescription(message: Int) {
        if (_description.value.isEmpty()) {
            _errordescription.value = message
        }
    }
    private fun updateErrorPrix(message: Int) {
        if (_prix.value.isEmpty()) {
            _errorprix.value = message
        }
    }
    private fun updateErrorNb(message: Int) {
        if (_nb.value.isEmpty()) {
            _errornb.value = message
        }
    }
    private fun updateErrorDate(message: Int) {
            _errordate.value = message
    }
    private fun isValidDateFormat(date: String): Boolean {
        val regex = Regex("""^\d{4}-\d{2}-\d{2}$""")
        return regex.matches(date)
    }

    fun onNextClicked() {
        showChoseDialog(
            title = TypeMessage.ResourceMessage(R.string.camera_dialog_titleee),
            message = TypeMessage.ResourceMessage(R.string.camera_dialog_titleee),
            ok = TypeMessage.ResourceMessage(R.string.oui),
            cancel = TypeMessage.ResourceMessage(R.string.non),
            //okActionBlock = {navigate()},
            cancelActionBlock = {}
        )
    }

}