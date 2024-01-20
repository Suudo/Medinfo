package com.example.medinfo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medinfo.R
import com.example.medinfo.ui.theme.textStyle12

@Composable
fun MedInfoTopBar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.topBarContent()
        ) {
            LanguageSwitch()
            Image(
                painter = painterResource(id = R.drawable.ic_messenger),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
        MedInfoPagerWithIndicator()
    }
}

@Composable
fun LanguageSwitch() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(24.dp)
    ) {
        Text(
            text = "ENG",
            style = textStyle12,
            color = Color(0xFFCCCCCC)
        )
        Spacer(modifier = Modifier
            .height(14.dp)
            .width(1.dp)
            .background(color = Color(0xFFCCCCCC))
        )
        Text(
            text = "GEO",
            style = textStyle12,
            color = Color(0xFF028FF6)
        )
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topBarContent() =
    padding(vertical = 8.dp)
        .height(24.dp)
        .fillMaxWidth()
        .background(Color.White)
        .padding(horizontal = 16.dp)


@Preview
@Composable
fun MedInfoTopBarPreview() {
    MedInfoTopBar()
}
