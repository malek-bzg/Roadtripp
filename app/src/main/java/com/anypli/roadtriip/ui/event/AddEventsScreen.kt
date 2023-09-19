package com.anypli.roadtriip.ui.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import java.util.*

@Composable
fun AddEventsScreen(
    viewModel: AddEventsViewModel ,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val titre by viewModel.titre.collectAsState()
    val description by viewModel.description.collectAsState()
    val prix by viewModel.prix.collectAsState()
    val date by viewModel.date.collectAsState()
    val nb by viewModel.nb.collectAsState()
    val errortitre by viewModel.errortitre.collectAsState()
    val errordescription by viewModel.errordescription.collectAsState()
    val errorprix by viewModel.errorprix.collectAsState()
    val errordate by viewModel.errordate.collectAsState()
    val errornb by viewModel.errornb.collectAsState()

    BaseScreen(
        viewModel = viewModel ,
        navigationCallback = {
            if (it is Navigation.CameraScreen)
                navigator.navigateTo(Navigation.CameraScreen)
        } ,
        snackbarHostState = snackbarHostState
    ) { modifier ->
        AddEventsScreen(
            snackbarHostState = snackbarHostState,
            modifier = modifier ,
            titre = titre ,
            description = description ,
            prix = prix ,
            date = date ,
            nb = nb,
            errortitre = errortitre,
            errordescription=errordescription,
            errornb = errornb,
            errorprix=errorprix,
            errordate =errordate,

            onCameraScreenClicked ={
                viewModel.onCameraScreenClicked()
            },
            onNextClicked ={
                viewModel.onNextClicked()
            },
            onAjouterClicked={
                viewModel.onAjouterClicked()
            },

            onTitreChange = {
                viewModel.onTitreChange(it)
            } ,
            onDescriptionChange ={
                viewModel.onDescriptionChange(it)
            },
            onPrixChange ={
                viewModel.onPrixChange(it)
            },
            onDateChange ={
                viewModel.onDateChange(it)
            },
            onNbDispoChange ={
                viewModel.onNbDispoChange(it)
            },
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddEventsScreen(
    snackbarHostState : SnackbarHostState = remember { SnackbarHostState()} ,
    modifier: Modifier ,
    titre: String ,
    description: String ,
    prix: String ,
    date: String ,
    nb : String ,
    onTitreChange: (String) -> Unit ,
    onDescriptionChange: (String) -> Unit ,
    onPrixChange: (String) -> Unit ,
    onDateChange: (String) -> Unit,
    onNbDispoChange: (String) -> Unit,
    onCameraScreenClicked: () -> Unit,
    onAjouterClicked: () -> Unit,
    onNextClicked: () -> Unit,
    errortitre: Int? ,
    errordescription: Int? ,
    errornb: Int? ,
    errordate: Int? ,
    errorprix : Int?,
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
                    contentDescription = stringResource(R.string.close),
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
            .padding(dimensionResource(R.dimen.margin_global_20dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .height(dimensionResource(R.dimen.dimen_global_220))
                .fillMaxWidth()
        )
        GlobalOutlinedTextField(
            value = titre ,
            onValueChange = onTitreChange  ,
            label = stringResource(R.string.titre) ,
            placeholder = stringResource(
                R.string.titre
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errortitre?.let {
                stringResource(id = it)
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))


        GlobalOutlinedTextField(
            value = date ,
            onValueChange = onDateChange,
            label = stringResource(R.string.date) ,
            placeholder = stringResource(
                R.string.date_placeholder
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errordate?.let {
                stringResource(id = it)
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))

        GlobalOutlinedTextField(
            value = prix ,
            onValueChange = onPrixChange,
            label = stringResource(R.string.prix) ,
            placeholder = stringResource(
                R.string.prix
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errorprix?.let {
                stringResource(id = it)
            }
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))

        GlobalOutlinedTextField(
            value = description ,
            onValueChange = onDescriptionChange,
            label = stringResource(R.string.description) ,
            placeholder = stringResource(
                R.string.description
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errordescription?.let {
                stringResource(id = it)
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_global_12dp)))

        GlobalOutlinedTextField(
            value = nb ,
            onValueChange = onNbDispoChange,
            label = stringResource(R.string.nombres_des_places_disponibles) ,
            placeholder = stringResource(
                R.string.nombres_des_places_disponibles
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ) ,
            singleLine = true,
            errorMessage = errornb?.let {
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
                    //onAjouterClicked()
                    onNextClicked ()
                },
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloows),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = dimensionResource(R.dimen.margin_global_8dp))
            ) {
                Text(text = stringResource(R.string.ajouter), fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { /* Action d'annulation */ },
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloows),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(R.dimen.margin_global_8dp))
            ) {
                Text(text = stringResource(R.string.annuler), fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Preview
@Composable
fun AddEventScreenPreview() {
    AddEventsScreen(
        modifier= Modifier ,
        titre = "titre",
        date = "date",
        description = "description",
        prix = "prix",
        nb = "nb",
        onTitreChange = {} ,
        onDescriptionChange= {} ,
        onPrixChange= {},
        onDateChange= {},
        onNbDispoChange= {},
        onCameraScreenClicked = {},
        onAjouterClicked = {},
        onNextClicked = {},
        errortitre = R.string.empty,
        errordescription = R.string.empty,
        errordate = R.string.empty,
        errorprix = R.string.empty,
        errornb=R.string.empty
    )
}
