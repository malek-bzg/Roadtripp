package com.anypli.roadtriip.map

import com.anypli.roadtriip.base.BaseViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.CameraPositionState


class MapViewModel: BaseViewModel()  {
    private val defaultCameraPosition = CameraPositionState(
        position = CameraPosition.fromLatLngZoom(LatLng(1.35, 103.87), 11f),
        //mapType = MapType.NORMAL
    )

    var cameraPositionState = defaultCameraPosition
        private set

    fun resetMap() {
        cameraPositionState = defaultCameraPosition
    }

//    fun zoomIn() {
//        cameraPositionState = cameraPositionState.copy(
//            position = cameraPositionState.position.zoomIn()
//        )
//    }
//
//    fun zoomOut() {
//        cameraPositionState = cameraPositionState.copy(
//            position = cameraPositionState.position.zoomOut()
//        )
//    }
}