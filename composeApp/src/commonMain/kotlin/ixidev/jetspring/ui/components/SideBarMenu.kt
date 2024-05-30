package ixidev.jetspring.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ixidev.jetspring.ui.navigation.Screens
import ixidev.jetspring.ui.navigation.navcontroller.NavController
import ixidev.jetspring.utils.stringResource

/***
 * Created by ixiDev on 21/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/
@Composable
fun SideBarMenu(modifier: Modifier = Modifier, navController: NavController) {
    val screens = listOf(
        Screens.Home,
        Screens.Settings,
        Screens.NewWindow,
    )
    val currentScreen by remember {
        navController.currentScreen
    }
    NavigationRail(
        modifier = modifier
            .fillMaxHeight()
    ) {
        AppMenuHeader()
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = Color.DarkGray)
        )
        screens.forEach {
            NavigationMenuItem(
                modifier = Modifier.fillMaxWidth(),
                selected = currentScreen == it,
                icon = it.icon!!,
                label = stringResource(it.label),
                onClick = {
                    if (it is Screens.NewWindow) {
                        navController.window(it)
                    } else {
                        navController.navigate(it)
                    }
                },
            )
        }
    }
}