package com.anypli.roadtriip.map

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen () {

    val tunis = LatLng(33.892166,9.561555499999997)
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(tunis, 10f)
    }
    val startPoint = LatLng(33.892166, 9.561555499999997)
    val endPoint = LatLng(34.000000, 9.000000) // Remplacez par les coordonnées de votre point de destination
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = tunis),
            title = "Tunisie" ,
            snippet = "Marker in Tunisie ",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        )

    }
}



