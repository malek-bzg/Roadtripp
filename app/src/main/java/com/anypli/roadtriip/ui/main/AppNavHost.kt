package com.anypli.roadtriip.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
 import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anypli.roadtriip.ui.resetpass.ResetPassScreen
import com.anypli.roadtriip.ui.signin.SigninScreen
import com.anypli.roadtriip.ui.signup.SignupScreen
import com.anypli.roadtriip.ui.splash.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.map.MapScreen
import com.anypli.roadtriip.ui.cars.CarsScreen
import com.anypli.roadtriip.ui.details.DetailsEventsScreen
import com.anypli.roadtriip.ui.details.MyEventsDetailsScreen
import com.anypli.roadtriip.ui.event.AddEventsScreen
import com.anypli.roadtriip.ui.event.CameraScreen
import com.anypli.roadtriip.ui.event.MyEventsScreen
import com.anypli.roadtriip.ui.home.HomeScreen
import com.anypli.roadtriip.ui.profile.ModifierProfileScreen
import com.anypli.roadtriip.ui.profile.ProfileScreen
import com.anypli.roadtriip.ui.resetpass.ChangermdpScreen

@Composable
fun AppNavHost(navHostController: NavHostController = rememberNavController()) {

     val navigator : AppNavigator = remember (navHostController){AppNavigatorImpl(navHostController)}
    //val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    NavHost(navHostController , startDestination = Navigation.SigninScreen.route, modifier = Modifier) {

        composable(route = Navigation.SplashScreen.route){
            SplashScreen(viewModel(),navigator)
        }
        composable(route = Navigation.SigninScreen.route){
            SigninScreen(viewModel() ,navigator)
        }
        composable(route = Navigation.SignupScreen.route){
            SignupScreen(viewModel(),navigator)
        }
        composable(route = Navigation.ResetPassScreen.route){
            ResetPassScreen(viewModel(),navigator)
        }
        composable(route = Navigation.HomeScreen.route) {
            HomeScreen(viewModel(), navigator)
        }
        composable(route = Navigation.DetailsEventsScreen.route){
            DetailsEventsScreen(viewModel(),navigator )
        }
        composable(route = Navigation.AddEventsScreen.route){
            AddEventsScreen(viewModel(),navigator )
        }
        composable(route = Navigation.CameraScreen.route){
            CameraScreen(viewModel(),navigator )
        }
        composable(route = Navigation.ModifierProfileScreen.route){
            ModifierProfileScreen(viewModel(),navigator)
        }
        composable(route = Navigation.ProfileScreen.route){
            ProfileScreen(viewModel(),navigator)
        }
        composable(route = Navigation.ChangermdpScreen.route){
            ChangermdpScreen(viewModel(),navigator)
        }
        composable(route = Navigation.CarsScreen.route){
            CarsScreen(viewModel(),navigator)
        }
        composable(route = Navigation.MapScreen.route){
            MapScreen()
        }
        composable(route = Navigation.MyEventsScreen.route){
            MyEventsScreen(viewModel(),navigator)
        }
        composable(route = Navigation.MyEventsDetailsScreen.route){
            MyEventsDetailsScreen(viewModel(),navigator)
        }
    }
}
interface AppNavigator {
    fun navigateTo(navigateTo: Navigation,  popUpTo: Navigation? = null, inclusive: Boolean = false )
    fun popBackStack(route: String? = null , inclusive: Boolean = false ): Boolean
}
class AppNavigatorImpl(private val navHostController: NavHostController) : AppNavigator {
    override fun navigateTo(navigateTo: Navigation,  popUpTo: Navigation? , inclusive: Boolean ) {
        navHostController.navigate(navigateTo.route){
            popUpTo?.let {
                popUpTo(it.route){
                    this.inclusive = inclusive
                }
            }
        }
    }
    override fun popBackStack(route: String? , inclusive: Boolean): Boolean {
        return if (route ==null ) {
            navHostController.popBackStack()
        }
        else{
            navHostController.popBackStack(route, inclusive)
        }
    }
}