package com.anypli.roadtriip.ui.profile

import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: BaseViewModel()  {

    private val userRepository = RepositoriesUtility.getUserRepository()



    var userData = MutableStateFlow<User?>(null)


init {
    viewModelScope.launch {
        val user = userRepository.getUser()
        userData.value = user
    }
}

    fun onCompteProfileClicked() {
        navigate(Navigation.ModifierProfileScreen)
    }


    fun onResetPassClicked() {
        navigate(Navigation.ChangermdpScreen)
    }
}