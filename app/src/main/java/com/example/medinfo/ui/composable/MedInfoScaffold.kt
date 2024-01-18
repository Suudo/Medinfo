package com.example.medinfo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.plusAssign
import com.example.medinfo.ui.screens.appCurrentDestinationAsState
import com.example.medinfo.ui.screens.destinations.Destination
import com.example.medinfo.ui.screens.startAppDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.spec.Route

@OptIn(ExperimentalMaterialNavigationApi::class)
@SuppressLint("RestrictedApi")
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

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    Scaffold(
        topBar = { topBar() },
        bottomBar = { bottomBar(destination) },
        content = content
    )

}