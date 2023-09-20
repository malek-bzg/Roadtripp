package com.anypli.roadtriip.global.helper

sealed class Navigation(val route: String) {

    object SplashScreen :Navigation ("splash")
    object SigninScreen :Navigation ("signin")
    object SignupScreen :Navigation ("signup")
    object ResetPassScreen :Navigation ("reset")
    object HomeScreen : Navigation("home")
    object DetailsEventsScreen : Navigation("details")
    object AddEventsScreen : Navigation("ajouterProd")
    object CameraScreen : Navigation("camera")
    object ModifierProfileScreen : Navigation("profile")
    object ProfileScreen : Navigation("profileScreen")
    object ChangermdpScreen : Navigation("changermdpScreen")
    object CarsScreen : Navigation("CarsScreen")
    object MapScreen : Navigation("MapScreen")
    object MyEventsScreen : Navigation("MyEventsScreen")
    object MyEventsDetailsScreen : Navigation("MyEventsDetailsScreen")
    object Back : Navigation("back")




}
