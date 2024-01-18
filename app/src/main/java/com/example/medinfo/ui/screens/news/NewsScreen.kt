package com.example.medinfo.ui.screens.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NewsScreen(navigator: DestinationsNavigator) {

    NewsContent()
}

@Composable
fun NewsContent() {
    LazyColumn(

        modifier = Modifier.fillMaxWidth()
    ) {

    }
}