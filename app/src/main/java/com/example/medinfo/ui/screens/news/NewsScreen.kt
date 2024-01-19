package com.example.medinfo.ui.screens.news

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medinfo.ui.NavigationAnimation
import com.example.medinfo.ui.screens.home.HomeContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun NewsScreen(navigator: DestinationsNavigator) {
    HomeContent(navigateToNews = { /*TODO*/ }, navigateToArchive = { /*TODO*/ }) {

    }
}

@Composable
fun NewsContent() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}