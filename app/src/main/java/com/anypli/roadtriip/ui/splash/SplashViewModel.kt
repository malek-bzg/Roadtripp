package com.anypli.roadtriip.ui.splash

import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.SPLASH_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: BaseViewModel() {

    init {
        viewModelScope.launch {
            delay(SPLASH_DURATION.toLong())
            navigate(Navigation.SigninScreen)
        }
    }

}