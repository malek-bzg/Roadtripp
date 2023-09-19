package com.anypli.roadtriip.ui.profile

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.fontDimensionResource
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme
import com.anypli.roadtriip.ui.shared.theme.radiusShape250dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(

    viewModel: ProfileViewModel ,
    navigator: AppNavigator


) {

    val snackbarHostState = remember { SnackbarHostState() }
    val userData by viewModel.userData.collectAsState()

    BaseScreen(
        viewModel = viewModel,
        navigationCallback = { navigation ->
            when (navigation) {
                is Navigation.CameraScreen -> navigator.navigateTo(Navigation.CameraScreen)
                is Navigation.ChangermdpScreen -> navigator.navigateTo(Navigation.ChangermdpScreen)
                is Navigation.ModifierProfileScreen -> navigator.navigateTo(Navigation.ModifierProfileScreen)
            }
        },
        snackbarHostState = snackbarHostState
    ) { modifier ->
        ProfileScreen(
            userData = userData,
            snackbarHostState = snackbarHostState,
            modifier = modifier,

            onCompteProfileClicked ={
                viewModel.onCompteProfileClicked()
            },
            onResetPassClicked ={
                viewModel.onResetPassClicked()
            },
        )
    }
}
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun ProfileScreen(
        userData: User?,
        snackbarHostState : SnackbarHostState = remember { SnackbarHostState () } ,
        modifier: Modifier ,
        onCompteProfileClicked: () -> Unit ,
        onResetPassClicked: () -> Unit ,

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
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.margin_global_20dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = dimensionResource(R.dimen.margin_global_35dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(9f),
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(top = dimensionResource(R.dimen.margin_global_10dp)) ,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { /* Action d'annulation */ }  ,
                            colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloows),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = dimensionResource(R.dimen.margin_global_8dp))
                        ) {
                            Text(text = stringResource(R.string.changer) , fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.weight(0.3f))

                        Button(
                            onClick = { /* Action d'annulation */ },
                            colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloows),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = dimensionResource(R.dimen.margin_global_8dp))
                        ) {
                            Text(text = stringResource(R.string.fermer) , fontWeight = FontWeight.Bold)
                        }

                    }
                }
                userData?.let { user ->
                    Column(
                        modifier = Modifier
                            .align( Alignment.Center )
                            .padding( top = dimensionResource(R.dimen.margin_global_20dp )),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(16.dp)) // Espace vide pour déplacer l'image vers le haut

                        // Utilisation de la forme circulaire pour la Card
                        Card(
                            modifier = Modifier
                                .size(dimensionResource(R.dimen.dimen_global_200)) // Ajustez la taille de la Card
                                .clip(CircleShape) // Forme circulaire pour la Card
                                .background(
                                    color = RoadtriipTheme.colors.black,
                                )
                        ) {
                            // Utilisation de la forme circulaire pour l'image
                            Image(
                                painter = rememberImagePainter(data = user.getUserPicture()),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize() // Remplissez toute la Card
                                    .clip(CircleShape) // Forme circulaire pour l'image
                            )
                        }
                        Text(
                            text = String.format(
                                "%s %s",
                                user.Fname ?: "Nom d'utilisateur inconnu",
                                user.Lname ?: ""
                            ),
                        )
                    }
                }


            }
            Button(
                onClick = {
                    onCompteProfileClicked()
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.margin_global_40dp)) ,
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.transparent)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_outline_edit_24) ,
                        contentDescription = null ,
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.margin_global_10dp))
                    )
                    Text(text = stringResource(R.string.compte_profil) , color = RoadtriipTheme.colors.black , fontSize = fontDimensionResource(R.dimen.font_15sp))
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24) ,
                        contentDescription = null ,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.margin_global_10dp))
                    )
                }
            }
            Button(
                onClick = { /* TODO: Implement */ } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.margin_global_10dp)) ,
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.transparent)
            ) {
                // Add the rest of the buttons
            }
            Button(
                onClick = {
                    onResetPassClicked()
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    //.align(Alignment.BottomStart)
                    .padding(bottom = dimensionResource(R.dimen.dimen_global_50)) ,
                colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.transparent)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_exit_to_app_24) ,
                        contentDescription = null ,
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.margin_global_10dp))
                    )
                    Text(text = stringResource(R.string.changer_le_mot_de_passe) , color = RoadtriipTheme.colors.black , fontSize =fontDimensionResource(R.dimen.font_15sp))
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24) ,
                        contentDescription = null ,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.margin_global_10dp)),
                    )
                }
            }
        }
    }
//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen(
//        modifier = Modifier ,
//
//    )



//}



