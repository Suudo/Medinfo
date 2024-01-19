package com.example.medinfo.ui.screens.archive

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.medinfo.ui.NavigationAnimation
import com.example.medinfo.ui.screens.home.HomeContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun ArchiveScreen(navigator: DestinationsNavigator) {

    HomeContent(navigateToNews = { /*TODO*/ }, navigateToArchive = { /*TODO*/ }) {

    }
}