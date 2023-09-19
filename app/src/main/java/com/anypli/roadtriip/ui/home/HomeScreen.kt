package com.anypli.roadtriip.ui.home

import HomeViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import com.anypli.roadtriip.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.utilities.shared
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anypli.roadtriip.AllDestinations
import com.anypli.roadtriip.DrawerHeader
import com.anypli.roadtriip.global.helper.Navigation.AddEventsScreen.route
import kotlinx.coroutines.launch
var index=2
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController ()

    BaseScreen(
        viewModel = viewModel,
        navigationCallback = { navigation ->
            when (navigation) {
                is Navigation.DetailsEventsScreen -> {
                 navigator.navigateTo(Navigation.DetailsEventsScreen)
                }
                is Navigation.ProfileScreen -> {
                   navigator.navigateTo(Navigation.ProfileScreen)
                }
                is Navigation.HomeScreen -> {
                    navigator.navigateTo(Navigation.HomeScreen)
                }
            }
        } ,
        snackbarHostState = snackbarHostState
    ) { modifier ->

        val gridViewItems by viewModel.gridViewItems.collectAsState()

        if (gridViewItems != null) {
           HomeScreen(
                snackbarHostState = snackbarHostState,
                modifier = modifier,
                gridViewItems = gridViewItems,
                onItemsClicked = {
                   viewModel.onItemsClicked()
                },
               navigateToHome = {
                   navController.navigate(Navigation.HomeScreen.route)
               },
               navigateToProfile = {
                   navController.navigate(Navigation.ProfileScreen.route)
               },
               navController = navController
//                onItemsProfile = {
//                    viewModel.onItemsProfile()
//               }
           )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class , ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun HomeScreen(
    snackbarHostState: SnackbarHostState = remember {SnackbarHostState()} ,
    modifier: Modifier ,
    gridViewItems: List<HomeGridView> ,
    onItemsClicked: (HomeGridView) -> Unit ,
    navController: NavController,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit

    // onItemsProfile: UserProfile-> Unit,

) {
    var isDrawerOpen by remember {mutableStateOf(false)}
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    Scaffold(
        modifier = Modifier ,
        topBar = {
            TopAppBar(
                title = {

                    Row(
                        verticalAlignment = Alignment.CenterVertically ,
                        modifier = Modifier.clickable {
                            isDrawerOpen = !isDrawerOpen
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu ,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_global_8dp)))
                        Text(text = stringResource(id = R.string.events))
                    }
                } ,
                actions = {
                    Icon(Icons.Filled.Search , contentDescription = null)
                }
            )
        }
    ) {
        Image(
            painter = painterResource( id = R.drawable.ic_shape ) ,
            contentDescription = "" ,
            modifier = Modifier.fillMaxSize() ,
            contentScale = ContentScale.FillBounds
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2) ,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_global_9dp)) ,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_global_9dp)),
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
                            ),
                            modifier = Modifier.weight(1f)

                        )
                    }
                }
            }
        }
        if (isDrawerOpen) {
            AppDrawer(
                navController = navController ,
                route = "ProfileScreen" ,
                navigateToHome = navigateToHome,
                navigateToProfile = navigateToProfile,
                closeDrawer = {
                    isDrawerOpen =
                        false // Fermez le tiroir lorsque l'utilisateur le ferme manuellement
                },
            )
        }
    }
}@OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun AppDrawer(
            route: String,
            modifier: Modifier = Modifier,
            navigateToHome: () -> Unit = {},
            navigateToProfile: () -> Unit = {},
            navController: NavController,
            closeDrawer: () -> Unit = {}
        ) {
            val navBackStackEntry by navController?.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            ModalDrawerSheet(modifier = Modifier) {
                DrawerHeader(modifier)
                //Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_global_30)))
                if (navController != null) {
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(id = R.string.home) ,
                                style = MaterialTheme.typography.labelSmall,
                            )
                        } ,
                        selected = currentRoute == Navigation.HomeScreen.route ,
                        onClick = {
                            navigateToHome()
                            //closeDrawer()
                        } ,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home ,
                                contentDescription = null
                            )
                        } ,
                        shape = MaterialTheme.shapes.small ,
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(id = R.string.profile),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        selected = currentRoute == Navigation.ProfileScreen.route,
                        onClick = {
                            navigateToProfile()
                           // closeDrawer()
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person ,
                                contentDescription = null
                            )
                        },
                        shape = MaterialTheme.shapes.small
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(id = R.string.myevents),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        selected = currentRoute == Navigation.ProfileScreen.route,
                        onClick = {
                            navigateToProfile()
                            // closeDrawer()
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Menu  ,
                                contentDescription = null
                            )
                        },
                        shape = MaterialTheme.shapes.small
                    )
                }
            }
        }
//        @Composable
//        fun DrawerHeader(modifier: Modifier) {
//
//            Column(
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.Start,
//                modifier = modifier
//                    .background(MaterialTheme.colorScheme.secondary)
//                    .padding(dimensionResource(id = R.dimen.dimen_global_30))
//                    .fillMaxWidth()
//            ) {
//                Image(
//                    painterResource(id = R.drawable.logo),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = modifier
//                        .size(dimensionResource(id = R.dimen.dimen_global_30))
//                        .clip(CircleShape)
//                )
//                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_global_30)))
//                Text(
//                    text = stringResource(id = R.string.app_name),
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onPrimary,
//                )
//                Text(
//                    text = stringResource(id = R.string.app_name),
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onPrimary,
//                )
//            }
//        }

//*////-----////*//

//        @Preview
//        @Composable
//        fun DrawerHeaderPreview() {
//            AppDrawer(modifier = Modifier, route = AllDestinations.HOME)
//        }

//        val coroutineScope = rememberCoroutineScope()
//        if (isDrawerOpen) {
//            MyDrawerContent (
//                navController = navController, // Passez le NavController ici
//                onItemSelected = { destination: String ->
//                isDrawerOpen = false // Fermer le tiroir
//                coroutineScope.launch {
//                    drawerState.close() // Fermer le Drawer après avoir effectué l'action souhaitée
//                }
//                snackbarHostState.currentSnackbarData?.dismiss()
//
//            }
//            )
//        }
//        data class MenuItem(val title: String , val icon: ImageVector , val destination: Any?)
//
//        @OptIn(ExperimentalMaterial3Api::class)
//        @Composable
//        private fun MyDrawerContent(
//            navController: NavController ,
//            onItemSelected: (String) -> Unit ,
//
//           // viewModel: HomeViewModel
//        ) {
//            var isDrawerOpen by remember { mutableStateOf(false) }
//            val menu = listOf(
//                MenuItem(
//                    title = stringResource(R.string.home),
//                    icon = Icons.Default.Home,
//                    destination = "home"
//                ),
//                MenuItem(
//                    title = stringResource(R.string.myevents) ,
//                    icon = Icons.Default.Favorite ,
//                    destination = "ajouterProd",
//                ),
//                MenuItem(
//                    title = stringResource(R.string.profile),
//                    icon = Icons.Default.AccountCircle,
//                    destination = "profileScreen"
//                ),
//
//
////                MenuItem(
////                    title = stringResource(R.string.cars),
////                    icon = Icons.Default.CarCrash,
////                    destination = "CarsScreen"
////                ),
////                MenuItem(
////                    title = stringResource(R.string.map),
////                    icon = Icons.Default.Map,
////                    destination = Navigation.DetailsEventsScreen
////                )
//            )
//            Column {
//                for (item in menu) {
//                    Row(
//                        modifier = Modifier.clickable {
//                            onItemSelected.invoke(item.destination as String)
//                            index=menu.indexOf(item)
//                        },
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(imageVector = item.icon, contentDescription = null)
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(text = item.title)
//                    }
//                }
//            }
//
//            ModalDrawerSheet() {
//                Column {
//                    Box(
//                        modifier = Modifier
//                            .height(190.dp)
//                            .fillMaxWidth()
//                            .background(color = RoadtriipTheme.colors.yeloowsc)
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.event),
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
//                                .size(140.dp)
//                                .padding(2.dp)
//                                .align(Alignment.BottomCenter)
//                        )
//                        // Reste de votre contenu à l'intérieur de la Box
//                    }
//                    LazyColumn {
//
//                        items(menu) { menuList ->
//                            NavigationDrawerItem(
//                                shape = androidx.compose.material3.MaterialTheme.shapes.small ,
//                                label = {
//                                    Text(
//                                        text = menuList.title ,
//                                        style = androidx.compose.material3.MaterialTheme.typography.labelMedium ,
//                                    )
//                                } ,
//                                selected = menuList == menu[index] ,
//                                icon = {
//                                    Icon(
//                                        imageVector = menuList.icon ,
//                                        contentDescription = menuList.title ,
//                                        tint = RoadtriipTheme.colors.black ,
//                                    )
//                                } ,
//                                onClick = {
//                                    when (menuList.destination) {
//                                        "home" ->{
//                                            navController.navigate(Navigation.HomeScreen.route)
//                                            //index=0
//                                        }
//                                        "ajouterProd" ->{
//                                            navController.navigate(Navigation.AddEventsScreen.route)
//                                            //System.exit(0)
//                                        }
//                                        "profileScreen" ->{
//                                        index=2
//                                            navController.navigate(Navigation.ProfileScreen.route)
//                                        }
////                                        "CarsScreen" -> {
////                                            Navigation.CarsScreen
////                                            //System.exit(0)
////                                        }
//
//                                    }
//                                },
//                            )
//                        }
//                    }
//                    Divider()
//                }
//            }

      //  }


data class  HomeGridView(
    val name: String ,
    val count: String ,
    val Image: String ,
    var desc:String=""
)

