package com.anypli.roadtriip.ui.details

import com.anypli.roadtriip.ui.profile.ModifierProfileViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.fontDimensionResource
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.component.GlobalOutlinedTextField
import com.anypli.roadtriip.ui.shared.component.GradientButton
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme
import com.anypli.roadtriip.ui.shared.theme.radiusShape30dp



@Composable
fun MyEventsDetailsScreen(

    viewModel: ModifierProfileViewModel ,
    navigator: AppNavigator ,

    ) {
    val snackbarHostState = remember { SnackbarHostState() }
    val userData by viewModel.userData.collectAsState()

    //val loggedInUser by viewModel.getLoggedInUserProfile().collectAsState()
    val name by viewModel.name.collectAsState()
    val lastname by viewModel.lastname.collectAsState()
    val email by viewModel.email.collectAsState()
    val errorMail by viewModel.errorMail.collectAsState()
    val errorname by viewModel.errorname.collectAsState()
    val errorlastname by viewModel.errorlastname.collectAsState()


    BaseScreen(
        viewModel = viewModel ,
        navigationCallback = {
            if (it is Navigation.SigninScreen) {
                navigator.navigateTo(Navigation.SigninScreen, popUpTo = Navigation.HomeScreen ,inclusive = true)
            }
        },
        snackbarHostState = snackbarHostState
    ) { modifier ->


        MyEventsDetailsScreen(
            userData = userData,
            snackbarHostState = snackbarHostState ,
            modifier = modifier ,
            name = name,
            email = email ,
            lastname= lastname,
            errorMail = errorMail,
            errorname=errorname,
            errorlastname = errorlastname,
            //  loggedInUser = userProfile,


            onNameChange = {
                viewModel.onNameChange(it)
            },
            onEmailChange = {
                viewModel.onEmailChange(it)
            },
            onLastNameChange= {
                viewModel.onLastNameChange(it)
            },
            onMiseAJourClicked ={
                viewModel.onMiseAJourClicked()
            },
            onDeletedClicked ={
                viewModel.onDeletedClicked()
            },
        )
    }
    //}
}
@Composable
private fun MyEventsDetailsScreen(
    userData: User? ,
    snackbarHostState: SnackbarHostState = remember {SnackbarHostState()} ,
    modifier: Modifier ,
    name: String ,
    lastname : String ,
    email : String ,
    onNameChange:(String)->Unit ,
    onEmailChange:(String)->Unit ,
    onLastNameChange:(String)->Unit ,
    onMiseAJourClicked: () -> Unit ,
    onDeletedClicked: () -> Unit ,
    errorMail: Int? ,
    errorname: Int? ,
    errorlastname: Int? ,
    //loggedInUser: UserProfile
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
            painter = painterResource(id = R.drawable.ic_shape ) , contentDescription = "" ,
            modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.margin_global_20dp)) ,
            verticalArrangement = Arrangement.SpaceBetween ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.compte_profil) ,
                        //fontFamily = FontFamily(Font(R.font.bebasneue_regular)) ,
                        fontSize = fontDimensionResource(R.dimen.font_35sp) ,
                        fontWeight = FontWeight.Bold ,
                        color = RoadtriipTheme.colors.black
                    )
                } ,
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation click */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_left_24) ,
                            contentDescription = stringResource(R.string.close)
                        )
                    }
                } ,
                backgroundColor = RoadtriipTheme.colors.transparent ,
                elevation = dimensionResource(R.dimen.margin_global_0dp)
            )
            Column(
                modifier = Modifier.weight(2f) ,
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        onDeletedClicked()
                    } ,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.margin_global_15dp)) ,
                    colors = ButtonDefaults.textButtonColors(contentColor = RoadtriipTheme.colors.red)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_outline_delete_forever_24) ,
                            contentDescription = null ,
                            modifier = Modifier.padding(end =dimensionResource(R.dimen.margin_global_8dp))
                        )
                        Text(
                            text = stringResource(R.string.supprimer_compte) ,
                            fontSize =fontDimensionResource(R.dimen.font_15sp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.weight(8f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.margin_global_50dp))) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.weight(1f)) {
                            GlobalOutlinedTextField(
                                value = name ,
                                //value =  loggedInUser?.Fname ?: "",
                                onValueChange = onNameChange ,
                                label = stringResource(R.string.name) ,
                                placeholder = stringResource(
                                    R.string.name
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Text
                                ) ,
                                singleLine = true,
                                errorMessage = errorname?.let {
                                    stringResource(id = it)
                                }
                            )
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_8dp)))
                        }
                        Column(Modifier.weight(1f)) {
                            GlobalOutlinedTextField(
                                //value =  loggedInUser?.Lname ?: "",
                                value = lastname ,
                                onValueChange = onLastNameChange  ,
                                label = stringResource(R.string.last_name) ,
                                placeholder = stringResource(
                                    R.string.last_name
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Text
                                ) ,
                                singleLine = true,
                                errorMessage = errorlastname?.let {
                                    stringResource(id = it)
                                }
                            )
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_8dp)))

                        }
                    }
                    GlobalOutlinedTextField(
                        //value =  loggedInUser?.email ?: "",
                        value =  email ,
                        onValueChange = onEmailChange  ,
                        label = stringResource(R.string.email_address) ,
                        placeholder = stringResource(
                            R.string.email_address
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        ) ,
                        singleLine = true,
                        errorMessage = errorMail?.let {
                            stringResource(id = it)
                        }
                    )
                }
                val gradientColor = listOf(RoadtriipTheme.colors.yeloows ,RoadtriipTheme.colors.yeloowsc)
                val cornerRadius =  dimensionResource(R.dimen.margin_global_16dp)


                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.margin_global_10dp)))

                GradientButton(
                    gradientColors = gradientColor,
                    cornerRadius = cornerRadius,
                    nameButton = stringResource(R.string.maj),
                    roundedCornerShape = RoadtriipTheme.shapes.radiusShape30dp,
                    onClick = {
                        onMiseAJourClicked()

                    }
                )
            }
        }
    }
}
//@Preview
//@Composable
//fun ModifierProfileScreenPreview() {
//    ModifierProfileScreen(
//        modifier = Modifier,
//        name = "name",
//        lastname = "lastname",
//        email = "email" ,
//        onNameChange = {} ,
//        onLastNameChange = {} ,
//        onMiseAJourClicked = {} ,
//        onEmailChange = {} ,
//        errorMail = R.string.empty,
//        errorname = R.string.empty,
//        errorlastname = R.string.empty,
//    )
//}





