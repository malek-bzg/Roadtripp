package com.anypli.roadtriip.ui.resetpass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.fontDimensionResource
import com.anypli.roadtriip.ui.shared.component.GradientButtonReset
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.component.GlobalOutlinedTextField
import com.anypli.roadtriip.ui.shared.theme.*


@Composable
fun ResetPassScreen(

    viewModel: ResetPassViewModel,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val email by viewModel.email.collectAsState()
    val errorMail by viewModel.errorMail.collectAsState()



    BaseScreen(
        viewModel = viewModel,
        navigationCallback = {
            if (it is Navigation.SignupScreen)
                navigator.navigateTo(Navigation.SignupScreen)
        },
        snackbarHostState = snackbarHostState
    ) { modifier ->
        ResetPassScreen(

            snackbarHostState = snackbarHostState,
            modifier = modifier,
            email = email,

            errorMail = errorMail,

            onEmailChange = {
                viewModel.onEmailChange(it)
            },

            onSignupClicked ={
                viewModel.onSignupClicked()
            },
            onResetClicked ={
                viewModel.onResetClicked()
            },



        )
    }
}


@Composable
private fun ResetPassScreen(
    snackbarHostState : SnackbarHostState = remember { SnackbarHostState() } ,
    modifier: Modifier ,
    email: String ,
    onSignupClicked: () -> Unit,
    onResetClicked:() -> Unit,
    onEmailChange:(String)->Unit ,
    errorMail: Int? ,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = RoadtriipTheme.colors.transparent ,
            )
    ) {
        Image(painter = painterResource(id = R.drawable.ic_shape), contentDescription = "",
            modifier = Modifier.fillMaxSize(),contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier

                .align(Alignment.BottomCenter),
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(dimensionResource(R.dimen.dimen_global_300))
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.margin_global_16dp))
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                ,

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //.........................Spacer
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_50dp)))

                //.........................Text: title
                androidx.compose.material3.Text(
                    text = stringResource(R.string.reset_password),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.dimen_global_130))
                        .fillMaxWidth(),
                    style = RoadtriipTheme.typography.fontSize24spBLack,
                    color = RoadtriipTheme.colors.black,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_8dp)))

                ResetEmailID(email, onEmailChange ,errorMail )
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_3dp)))

                val gradientColor = listOf(RoadtriipTheme.colors.yeloows ,RoadtriipTheme.colors.yeloowsc)
                val cornerRadius =dimensionResource(R.dimen.margin_global_16dp)

                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_10dp)))
                GradientButtonReset(
                    gradientColors = gradientColor,
                    cornerRadius = cornerRadius,
                    nameButton = stringResource(R.string.submit),
                    roundedCornerShape = RoadtriipTheme.shapes.radiusShape30dp,
                    onClick = {
                        onResetClicked()

                    }

                )
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_10dp)))
                androidx.compose.material3.TextButton(onClick = {
                    onSignupClicked()

                }) {
                    androidx.compose.material3.Text(
                        text = stringResource(R.string.sign_up),
                        letterSpacing = fontDimensionResource(R.dimen.font_1sp) ,
                        style = RoadtriipTheme.typography.fontSize14spBLack
                    )
                }

                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_5dp)))
            }
        }
    }

}

//...........................................................................


//email id
@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun ResetEmailID(email: String, onEmailChange: (String) -> Unit ,errorMail: Int? = null) {
    GlobalOutlinedTextField(
        value = email ,
        onValueChange = onEmailChange  ,
        label = stringResource(R.string.enter_registered_email) ,
        placeholder = stringResource(
            R.string.enter_registered_email
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ) ,
        singleLine = true,
        errorMessage = errorMail?.let {
            stringResource(id = it)
        },

    )
}
@Preview
@Composable
fun ResetPassScreenPreview() {
    ResetPassScreen(
        modifier = Modifier ,
        email = "email" ,
        onSignupClicked = {}  ,
        onResetClicked = {}  ,
        onEmailChange = {} ,
        errorMail = R.string.empty,

    )
}