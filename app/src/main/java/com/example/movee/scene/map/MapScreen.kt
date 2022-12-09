package com.example.movee.scene.map

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movee.R
import com.example.movee.ui.theme.MoveeTheme
import com.example.movee.util.permission.PermissionAction
import com.example.movee.util.permission.PermissionUI
import com.google.android.gms.auth.api.phone.SmsCodeAutofillClient.PermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch


@Composable
fun MapScreen() {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val permissionState = remember{ mutableStateOf<PermissionAction>(PermissionAction.OnPermissionDenied) }
    val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val myLocation = remember{ mutableStateOf(Pair(0.0,0.0)) }
    LaunchedEffect(permissionState.value == PermissionAction.OnPermissionGranted){
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            myLocation.value = Pair(it.latitude,it.longitude)
        }
    }


    PermissionUI(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION,
        stringResource(id = R.string.permission_location_rationale),
        scaffoldState
    ) { permissionAction ->
        permissionState.value = permissionAction
        when (permissionAction) {
            is PermissionAction.OnPermissionGranted -> {
                //Todo: do something now as we have location permission
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Location permission granted!")
                }
            }
            is PermissionAction.OnPermissionDenied -> {
                //permissionTestViewModel.setPerformLocationAction(false)
            }
        }
    }


    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition(LatLng(myLocation.value.first,myLocation.value.second),10f,1.0F,1.0F)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = permissionState.value == PermissionAction.OnPermissionGranted)
    ) {
    }

}

@Preview
@Composable
fun MapScreenPrev() {
    MoveeTheme {
        MapScreen()

    }
}