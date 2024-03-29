package com.example.medinfo.ui.screens.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.medinfo.ui.animation.NavigationAnimation
import com.ramcosta.composedestinations.annotation.Destination

@Destination(style = NavigationAnimation::class)
@Composable
fun AboutScreen() {
   Box(
       contentAlignment = Alignment.Center,
       modifier = Modifier.fillMaxSize()
   ) {
       Text(text = "About Screen")
   }
}