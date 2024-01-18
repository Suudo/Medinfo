package com.example.medinfo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medinfo.ui.composable.MedInfoBottomBar
import com.example.medinfo.ui.composable.MedInfoScaffold
import com.example.medinfo.ui.composable.MedInfoTopBar
import com.example.medinfo.ui.main.MainViewModel
import com.example.medinfo.ui.screens.NavGraphs
import com.example.medinfo.ui.screens.destinations.HomeScreenDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun MedInfo() {

    val engine = rememberAnimatedNavHostEngine()
    val navController = engine.rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()
    val startRoute = HomeScreenDestination

    MedInfoScaffold(
        navController = navController,
        startRoute = startRoute,
        topBar = { MedInfoTopBar() },
        bottomBar = { MedInfoBottomBar(navController) }
    ) {
        DestinationsNavHost(
            engine = engine,
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.fillMaxSize(),
            startRoute = startRoute
        )
    }

}
