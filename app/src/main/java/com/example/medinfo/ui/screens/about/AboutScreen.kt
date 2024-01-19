package com.example.medinfo.ui.screens.about

import androidx.compose.runtime.Composable
import com.example.medinfo.ui.NavigationAnimation
import com.example.medinfo.ui.screens.home.HomeContent
import com.example.medinfo.ui.screens.home.HomeScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun AboutScreen() {
    HomeContent(navigateToNews = { /*TODO*/ }, navigateToArchive = { /*TODO*/ }) {
        
    }
}