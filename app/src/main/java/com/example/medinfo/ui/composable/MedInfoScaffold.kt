package com.example.medinfo.ui.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.medinfo.ui.screens.appCurrentDestinationAsState
import com.example.medinfo.ui.screens.destinations.Destination
import com.example.medinfo.ui.screens.startAppDestination
import com.ramcosta.composedestinations.spec.Route

@Composable
fun MedInfoScaffold(
    startRoute: Route,
    navController: NavHostController,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable (Destination) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {

    val destination = navController.appCurrentDestinationAsState().value
        ?: startRoute.startAppDestination

    Scaffold(
        backgroundColor = Color.White,
        topBar = { topBar() },
        bottomBar = { bottomBar(destination) },
        content = content
    )

}