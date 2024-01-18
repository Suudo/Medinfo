package com.example.medinfo.ui.screens.archive

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ArchiveScreen(navigator: DestinationsNavigator) {

    Box(contentAlignment = Alignment.Center) {
        Text(text = "Archive")
    }
}