package com.anypli.roadtriip.ui.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel: BaseViewModel()  {

    fun onBackClicked(){
        navigate(Navigation.Back)
    }

//    fun onParticipeClicked() {
//        navigate(Navigation.ComposeMapCenterPointMapMarker)
//    }
}