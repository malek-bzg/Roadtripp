package com.anypli.roadtriip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    route: String,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    closeDrawer: () -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_global_30)))
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route == AllDestinations.HOME,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            icon = {Icon(imageVector = Icons.Default.Home, contentDescription = null )},
            shape = MaterialTheme.shapes.small
        )
        NavigationDrawerItem(
            label = {Text(text = stringResource(id = R.string.profile), style = MaterialTheme.typography.labelSmall)},
            selected = route == AllDestinations.PROFILE ,
            onClick = {
                navigateToProfile()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            shape = MaterialTheme.shapes.small
        )
    }
}

@Composable
fun DrawerHeader(modifier: Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(dimensionResource(id = R.dimen.dimen_global_30))
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(dimensionResource(id = R.dimen.dimen_global_30))
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_global_30)))

        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    AppDrawer(modifier = Modifier, route = AllDestinations.HOME)
}