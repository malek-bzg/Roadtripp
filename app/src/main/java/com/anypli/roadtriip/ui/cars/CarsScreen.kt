package com.anypli.roadtriip.ui.cars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.component.GlobalOutlinedTextField
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme
import com.anypli.roadtriip.ui.shared.theme.radiusShape250dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@Composable
fun CarsScreen(
    viewModel: CarsViewModel ,
    navigator: AppNavigator
){
    val snackbarHostState = remember { SnackbarHostState() }
    val errorname by viewModel.errorname.collectAsState()
    val errorcolor by viewModel.errorcolor.collectAsState()

    val name by viewModel.name.collectAsState()
    val color by viewModel.color.collectAsState()
    val userId by viewModel.userId.collectAsState()


    BaseScreen(
        viewModel = viewModel ,
        navigationCallback = {
            if (it is Navigation.CameraScreen)
                navigator.navigateTo(Navigation.CameraScreen)
        } ,
        snackbarHostState = snackbarHostState
    ) { modifier ->
        CarsScreen(
            snackbarHostState = snackbarHostState,
            modifier = modifier ,
            name = name ,
            userId = userId,
            color = color ,
            errorname = errorname,
            errorcolor=errorcolor,
            onColorChange = {
                viewModel.onColorChange(it)
            } ,
            onNameChange ={
                viewModel.onNameChange(it)
            },
            onAjouterClicked={
                viewModel.onAjouterClicked()
            },
            onUserIdChange ={
                viewModel.onUserIdChange(it)
            },

        )
    }
}
@OptIn(ExperimentalMaterial3Api::class , ExperimentalGlideComposeApi::class)
@Composable
private fun CarsScreen(
    snackbarHostState : SnackbarHostState = remember { SnackbarHostState() } ,
    modifier: Modifier ,
    name: String ,
    color: String ,
    userId: String ,
    onNameChange: (String) -> Unit ,
    onColorChange: (String) -> Unit ,
    onUserIdChange: (String) -> Unit ,
    //onCameraScreenClicked: () -> Unit ,
    onAjouterClicked: () -> Unit ,
    errorname: Int? ,
    errorcolor: Int? ,

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.margin_global_20dp))
    ) {
        IconButton(
            onClick = { /* Action à effectuer lors du clic */ },
            modifier = Modifier.wrapContentHeight(),
            content = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.close) ,
                )
            }
        )
    }
    Image(
        painter = painterResource(id = R.drawable.ic_shape) , contentDescription = "" ,
        modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(dimensionResource(R.dimen.margin_global_15dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


//        Image(
//            painter = painterResource(id = R.drawable.logo) ,
//            contentDescription = null,
//            modifier = Modifier
//                .height(dimensionResource(R.dimen.dimen_global_150))
//                .fillMaxWidth()
//        )
        Card(
            modifier = Modifier
                .size(dimensionResource(R.dimen.dimen_global_120))
                .background(
                    color = RoadtriipTheme.colors.black ,
                    shape = RoadtriipTheme.shapes.radiusShape250dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_add) , contentDescription = "" ,
                modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillBounds
            )
        }
        
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_global_200)))


        GlobalOutlinedTextField(
            value = name ,
            onValueChange = onNameChange  ,
            label = stringResource(R.string.carname) ,
            placeholder = stringResource(
                R.string.carname
            ) ,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errorname?.let {
                stringResource(id = it)
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))


        GlobalOutlinedTextField(
            value = color ,
            onValueChange = onColorChange,
            label = stringResource(R.string.color) ,
            placeholder = stringResource(
                R.string.color
            ) ,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errorcolor?.let {
                stringResource(id = it)
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))
        GlobalOutlinedTextField(
            value = userId ,
            onValueChange = onUserIdChange  ,
            label = stringResource(R.string.userId) ,
            placeholder = stringResource(
                R.string.userId
            ) ,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errorname?.let {
                stringResource(id = it)
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.margin_global_50dp))
        ) {
            Button(
                onClick = {
                    //onCameraScreenClicked()
                    onAjouterClicked()
                },
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloows),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = dimensionResource(R.dimen.margin_global_8dp))
            ) {
                Text(text = stringResource(R.string.ajouter) , fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { /* Action d'annulation */ },
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloows),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(R.dimen.margin_global_8dp))
            ) {
                Text(text = stringResource(R.string.annuler) , fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
fun CarScreenPreview() {
    CarsScreen(
        modifier= Modifier ,
        name = "name",
        color = "color",
        userId ="userId",
        onNameChange = {} ,
        onUserIdChange = {} ,
        onColorChange= {} ,
        onAjouterClicked = {},
        errorname = R.string.empty,
        errorcolor = R.string.empty,
    )
}