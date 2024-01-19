package com.example.medinfo.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.example.medinfo.R
import com.example.medinfo.ui.screens.NavGraphs
import com.example.medinfo.ui.screens.appCurrentDestinationAsState
import com.example.medinfo.ui.screens.destinations.AboutScreenDestination
import com.example.medinfo.ui.screens.destinations.ArchiveScreenDestination
import com.example.medinfo.ui.screens.destinations.Destination
import com.example.medinfo.ui.screens.destinations.DirectionDestination
import com.example.medinfo.ui.screens.destinations.HomeScreenDestination
import com.example.medinfo.ui.screens.destinations.NewsScreenDestination
import com.example.medinfo.ui.screens.startAppDestination
import com.example.medinfo.ui.theme.textStyle11
import com.ramcosta.composedestinations.navigation.navigate


@Composable
fun MedInfoBottomBar(navController: NavHostController) {

    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    MedInfoBottomNavigation(currentDestination) { destination, selected ->
        BottomNavigationItem(
            selected = selected,
            icon = { BottomBarIcon(destination, selected) },
            label = { BottomBarLabel(destination.label, selected) },
            onClick = { navController.navigate(destination.direction) }
        )
    }
}

@Composable
fun MedInfoBottomNavigation(
    currentDestination: Destination,
    content: @Composable RowScope.(BottomBarItem, Boolean) -> Unit
) {
    BottomNavigation(backgroundColor = Color.White) {
        BottomBarItem.values().forEach {
            val isSelected = currentDestination == it.direction
            content(it, isSelected)
        }
    }
}

@Composable
fun BottomBarIcon(destination: BottomBarItem, isSelected: Boolean) {
    Icon(
        imageVector = ImageVector.vectorResource(id = destination.icon),
        tint = if (isSelected) Color(0xFF028FF6) else Color(0xFF2D2E2B),
        contentDescription = ""
    )
}

@Composable
fun BottomBarLabel(label: String, isSelected: Boolean) {
    Text(
        text = label,
        style = textStyle11,
        color = if (isSelected) Color(0xFF028FF6) else Color(0xFF2D2E2B)
    )
}

enum class BottomBarItem(
    val direction: DirectionDestination,
    val icon: Int,
    val label: String,
) {
    Home(HomeScreenDestination, R.drawable.ic_home, "მთავარი"),
    News(NewsScreenDestination, R.drawable.ic_news, "სიახლე"),
    Archive(ArchiveScreenDestination, R.drawable.ic_archive, "არქივი"),
    About(AboutScreenDestination, R.drawable.ic_about, "შესახებ")
}