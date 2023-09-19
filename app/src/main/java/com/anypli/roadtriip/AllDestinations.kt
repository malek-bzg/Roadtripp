package com.anypli.roadtriip


import androidx.navigation.NavHostController
import com.anypli.roadtriip.AllDestinations.HOME
import com.anypli.roadtriip.AllDestinations.PROFILE
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.global.helper.Navigation

object AllDestinations {
    const val HOME = "Home"
    const val PROFILE = "ProfileScreen"
}

class AppNavigationActions(private val navController: NavHostController): BaseViewModel()  {

    fun navigateToHome() {
        navigate(Navigation.HomeScreen)
    }

    fun navigateToProfile() {
        navigate(Navigation.ProfileScreen)
           // launchSingleTop = true
           // restoreState = true

    }
}