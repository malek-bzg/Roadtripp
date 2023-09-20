package com.anypli.roadtriip.ui.event

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.shared
import com.anypli.roadtriip.ui.home.HomeGridView
import com.anypli.roadtriip.ui.home.index
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyEventsScreen(
    viewModel: MyEventsViewModel,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController ()

    BaseScreen(
        viewModel = viewModel,
        navigationCallback = { navigation ->
            when (navigation) {
                is Navigation.MyEventsDetailsScreen -> {
                    navigator.navigateTo(Navigation.MyEventsDetailsScreen)
                }
                is Navigation.ProfileScreen -> {
                    navigator.navigateTo(Navigation.ProfileScreen)
                }
                is Navigation.HomeScreen -> {
                    navigator.navigateTo(Navigation.HomeScreen)
                }
                is Navigation.Back -> {
                    navigator.navigateTo(Navigation.Back)
                }
            }
        } ,
        snackbarHostState = snackbarHostState
    ) { modifier ->

        val gridViewItems by viewModel.gridViewItems.collectAsState()

        if (gridViewItems != null) {
            MyEventsScreen(
                snackbarHostState = snackbarHostState,
                modifier = modifier,
                gridViewItems = gridViewItems,
                onItemsClicked = {
                    viewModel.onItemsClicked()
                },
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class , ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MyEventsScreen(
    snackbarHostState: SnackbarHostState = remember {SnackbarHostState()} ,
    modifier: Modifier ,
    gridViewItems: List<HomeGridView> ,
    onItemsClicked: (HomeGridView) -> Unit ,
) {
    Scaffold(
        modifier = Modifier ,
        topBar = {
            TopAppBar(
                title = {

                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_global_8dp)))
                        Text(text = stringResource(id = R.string.my_events))
                } ,
                actions = {
                    Icon(Icons.Filled.Search , contentDescription = null)
                }
            )
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_shape) ,
            contentDescription = "" ,
            modifier = Modifier.fillMaxSize() ,
            contentScale = ContentScale.FillBounds
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2) ,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_global_9dp)) ,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_global_9dp)) ,
        ) {
            items(gridViewItems.size) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clickable {
                            onItemsClicked(gridViewItems[it])
                            shared.currentItems = it
                            shared.list = gridViewItems.toMutableList()
                        }
                ) {
                    GlideImage(
                        model = "http://192.168.30.1:8009${gridViewItems[it].Image}" ,
                        contentDescription = null ,
                        contentScale = ContentScale.Crop ,
                        modifier = Modifier.height(dimensionResource(R.dimen.dimen_global_200))
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(R.dimen.margin_global_30dp))
                            .align(Alignment.BottomCenter)
                            .background(RoadtriipTheme.colors.black.copy(alpha = 0.4f))
                            .padding(horizontal = dimensionResource(R.dimen.margin_global_5dp)) ,
                        Arrangement.Center ,
                        Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${gridViewItems[it].name}" ,
                            style = TextStyle(
                                color = RoadtriipTheme.colors.white
                            ) ,
                            modifier = Modifier
                                .clickable {
                                }
                                .weight(1f)
                        )
                        Text(
                            text = "${gridViewItems[it].count}" ,
                            style = TextStyle(
                                color = RoadtriipTheme.colors.white
                            ) ,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = {} ,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit ,
                                contentDescription = stringResource(id = R.string.annuler) ,
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

data class  HomeGridView(
    val name: String ,
    val count: String ,
    val Image: String ,
    var desc:String=""
)