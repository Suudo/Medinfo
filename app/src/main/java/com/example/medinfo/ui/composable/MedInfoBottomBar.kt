package com.example.medinfo.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medinfo.R
import com.example.medinfo.ui.screens.NavGraphs
import com.example.medinfo.ui.screens.destinations.ArchiveScreenDestination
import com.example.medinfo.ui.screens.destinations.DirectionDestination
import com.example.medinfo.ui.screens.destinations.HomeScreenDestination
import com.example.medinfo.ui.screens.destinations.NewsScreenDestination
import com.example.medinfo.ui.theme.textStyle11
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.isRouteOnBackStack

@Composable
fun MedInfoBottomBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.White) {
        BottomBarItem.values().forEach { destination ->
            val isCurrentDestOnBackStack = navController.isRouteOnBackStack(destination.direction)
            BottomNavigationItem(
                selected = isCurrentDestOnBackStack,
                icon = { BottomBarIcon(destination, isCurrentDestOnBackStack) },
                label = { BottomBarLabel(label = destination.label) },
                selectedContentColor = Color(0xFF028FF6),
                unselectedContentColor = Color(0xFF2D2E2B),
                onClick = {
                    if (isCurrentDestOnBackStack) {
                        navController.popBackStack(destination.direction, false)
                        return@BottomNavigationItem
                    }
                    navigate(navController, destination.direction.route)
                },
            )
        }
    }
}

fun navigate(
    navController: NavHostController,
    route: String
) {
    navController.navigate(route) {
        popUpTo(NavGraphs.root) { saveState = true }
        launchSingleTop = true
        restoreState = true
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
fun BottomBarLabel(label: String) {
    Text(
        text = label,
        style = textStyle11
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
    About(ArchiveScreenDestination, R.drawable.ic_about, "შესახებ")
}