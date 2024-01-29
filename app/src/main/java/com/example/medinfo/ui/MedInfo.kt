package com.example.medinfo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.plusAssign
import com.example.medinfo.ui.composable.MedInfoBottomBar
import com.example.medinfo.ui.composable.MedInfoPagerWithIndicator
import com.example.medinfo.ui.composable.MedInfoScaffold
import com.example.medinfo.ui.composable.MedInfoTopBar
import com.example.medinfo.ui.main.MainViewModel
import com.example.medinfo.ui.screens.NavGraphs
import com.example.medinfo.ui.screens.destinations.HomeScreenDestination
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@OptIn(
    ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MedInfo() {

    val engine = rememberAnimatedNavHostEngine()
    val navController = engine.rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()
    val startRoute = HomeScreenDestination

    val state by viewModel.state.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { true },
        skipHalfExpanded = true
    )

    val bottomSheetNavigator = remember { BottomSheetNavigator(sheetState) }
    navController.navigatorProvider += bottomSheetNavigator

    MedInfoScaffold(
        navController = navController,
        startRoute = startRoute,
        topBar = { MedInfoTopBar() },
        bottomBar = { MedInfoBottomBar(navController) }
    ) {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            modifier = Modifier.fillMaxSize(),
            sheetBackgroundColor = Color.White
        ) {
            DestinationsNavHost(
                engine = engine,
                navController = navController,
                navGraph = NavGraphs.root,
                modifier = Modifier.fillMaxSize(),
                startRoute = startRoute
            )
            MedInfoPagerWithIndicator(state.slider)
        }
    }

}
