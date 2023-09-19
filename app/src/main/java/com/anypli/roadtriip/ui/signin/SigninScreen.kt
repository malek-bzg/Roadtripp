package com.anypli.roadtriip.ui.signin

import SigninViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.fontDimensionResource
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.component.GlobalOutlinedTextField
import com.anypli.roadtriip.ui.shared.component.GlobalPasswordOutlinedTextField
import com.anypli.roadtriip.ui.shared.component.GradientButton
import com.anypli.roadtriip.ui.shared.theme.*

@Composable
fun SigninScreen(

    viewModel: SigninViewModel ,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordHidden by viewModel.passwordHidden.collectAsState()
    val errorMail by viewModel.errorMail.collectAsState()
    val errorPass by viewModel.errorPass.collectAsState()
    val context = LocalContext.current


    BaseScreen(
        viewModel = viewModel ,
        navigationCallback = { navigation ->
            when (navigation) {
                is Navigation.SignupScreen -> navigator.navigateTo(Navigation.SignupScreen)
                is Navigation.ResetPassScreen -> navigator.navigateTo(Navigation.ResetPassScreen)
                is Navigation.ProfileScreen -> navigator.navigateTo(Navigation.ProfileScreen)
                is Navigation.HomeScreen -> navigator.navigateTo(Navigation.SigninScreen)
            }

        },
        snackbarHostState = snackbarHostState
    ) { modifier ->
        SigninScreen(

            snackbarHostState = snackbarHostState ,
            modifier = modifier ,
            email = email ,
            password = password ,
            passwordHidden = passwordHidden,


            errorMail = errorMail ,
            errorPass=errorPass ,

            onEmailChange = {
                viewModel.onEmailChange(it)
            } ,
            onPasswordChange = {
                viewModel.onPasswordChange(it)
            } ,
            onPasswordHiddenChange = {
                viewModel.onPasswordHiddenChange(it)
            },
            onResetPasswordClicked ={
                viewModel.onResetPasswordClicked()
            },
            onCreateAccountClicked ={
                viewModel.onCreateAccountClicked()
            },
            onLoginClicked ={
                viewModel.onLoginClicked(context)
            },

        )
    }
}
@Composable
private fun SigninScreen(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() } ,
    modifier: Modifier ,
    email: String ,
    password: String ,
    passwordHidden: Boolean ,
    onPasswordHiddenChange: (Boolean) -> Unit ,
    onPasswordChange: (String) -> Unit ,
    onEmailChange: (String) -> Unit ,
    onResetPasswordClicked: () -> Unit ,
    onCreateAccountClicked: () -> Unit ,
    onLoginClicked: () -> Unit ,
    errorMail: Int? ,
    errorPass: Int? ,
    ) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = RoadtriipTheme.colors.transparent ,
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_shape) , contentDescription = "" ,
            modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter) ,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo) ,
                contentDescription = null ,
                contentScale = ContentScale.Fit ,
                modifier = Modifier
                    .height(dimensionResource(R.dimen.global_logo_height))
                    .fillMaxWidth() ,
                )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.margin_global_16dp))
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()) ,

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //.........................Spacer
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.hight_button)))

                //.........................Text: title
                Text(
                    text = stringResource(R.string.sign_in) ,
                    textAlign = TextAlign.Center ,
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.dimen_global_130))
                        .fillMaxWidth() ,
                    style = RoadtriipTheme.typography.fontSize24spBLack ,
                    color = RoadtriipTheme.colors.black ,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_8dp)))
                SimpleOutlinedTextFieldSample(email , onEmailChange , errorMail)
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_3dp)))
                SimpleOutlinedPasswordTextField(
                    password ,
                    onPasswordChange ,
                    passwordHidden ,
                    onPasswordHiddenChange ,
                    errorPass ,
                )
                val gradientColor = listOf(RoadtriipTheme.colors.yeloows , RoadtriipTheme.colors.yeloowsc)
                val cornerRadius = dimensionResource(R.dimen.margin_global_16dp)

                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_10dp)))
                GradientButton(
                    gradientColors = gradientColor ,
                    cornerRadius = cornerRadius ,
                    nameButton = stringResource(R.string.login) ,
                    roundedCornerShape = RoadtriipTheme.shapes.radiusShape30dp,
                    onClick = {
                        onLoginClicked()
                    }
                )
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_10dp)))
                TextButton(onClick = {
                    onCreateAccountClicked()
                }) {
                    Text(
                        color = RoadtriipTheme.colors.black ,
                        text = stringResource(R.string.create_an_account) ,
                        letterSpacing = fontDimensionResource(R.dimen.font_1sp) ,
                        style = RoadtriipTheme.typography.fontSize14spBLack
                    )
                }
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_5dp)))
                TextButton(onClick = {
                    onResetPasswordClicked()
                }) {
                    Text(
                        text = stringResource(R.string.reset_password) ,
                        letterSpacing = fontDimensionResource(R.dimen.font_1sp) ,
                        style = RoadtriipTheme.typography.fontSize14spBLack ,
                        color = RoadtriipTheme.colors.black ,
                    )
                }
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_20dp)))
            }
        }
    }
}
//email
@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample(email: String , onEmailChange: (String) -> Unit , errorMail: Int? = null ) {
        GlobalOutlinedTextField(
            value = email ,
            onValueChange = onEmailChange ,
            label = stringResource(R.string.name_or_email_address) ,
            placeholder = stringResource(
                R.string.name_or_email_address
            ) ,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next ,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true ,
            errorMessage = errorMail?.let {
                stringResource(id = it)
            }
        )
}
//password
@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedPasswordTextField(
    password: String ,
    onPasswordChange: (String) -> Unit ,
    passwordHidden: Boolean ,
    onPasswordHiddenChange: (Boolean) -> Unit,
    errorPass: Int? = null
) {
    GlobalPasswordOutlinedTextField (
        errorPass = errorPass?.let {
            stringResource(id = it)
        },
        value = password ,
        onValueChange = onPasswordChange ,
        label = stringResource(R.string.enter_password) ,
        passwordHidden = passwordHidden,
        onPasswordHiddenChange = onPasswordHiddenChange ,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next ,
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        )
}
@Preview
@Composable
fun SigninScreenPreview() {
    SigninScreen(
        modifier = Modifier ,
        email = "email" ,
        password = "password" ,
        passwordHidden = false ,
        onPasswordHiddenChange = {} ,
        onPasswordChange = {} ,
        onEmailChange = {} ,
        onResetPasswordClicked = {} ,
        onCreateAccountClicked = {} ,
        onLoginClicked = {} ,
        errorMail =R.string.invalid_email_format,
        errorPass =  R.string.empty
    )
}

