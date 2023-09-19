package com.anypli.roadtriip.ui.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.R
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.ui.main.AppNavigator

@Composable
fun SplashScreen(
    viewModel: SplashViewModel ,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }

    BaseScreen(
        viewModel = viewModel ,
        navigationCallback =
        {
            if (it is Navigation.SigninScreen)
                navigator.navigateTo(Navigation.SigninScreen , popUpTo =Navigation.SplashScreen , inclusive = true)

        } ,
        snackbarHostState = snackbarHostState
    ) { modifier ->
        SplashScreen(
            modifier = modifier
        )
    }
}


@Composable
private fun SplashScreen(
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)

    )

    {
        Image(
            painter = painterResource(id = R.drawable.ic_shape) , contentDescription = "" ,
            modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillBounds
        )
        // Logo centré
        Image(
            painter = painterResource(id = R.drawable.logo) ,
            contentDescription = null ,
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.Center)

        )
    }


}
@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        modifier = Modifier ,
    )
}



