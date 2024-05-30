package ixidev.jetspring.ui.navigation.navcontroller

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import ixidev.jetspring.base.BaseViewModel
import ixidev.jetspring.ui.navigation.Screens

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/
class NavigationHost(
    val navController: NavController,
    val windowScreens: (@Composable NavigationGraphBuilder.() -> Unit)? = null,
    val mainScreens: @Composable NavigationGraphBuilder.() -> Unit
) {

    @Composable
    fun build() {
        NavigationGraphBuilder().renderContents()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController
    ) {
        @Composable
        fun renderContents() {
            this@NavigationHost.mainScreens(this)
            this@NavigationHost.windowScreens?.invoke(this)
        }
    }
}


/**
 * Composable to build the Navigation Host
 */
@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: Screens,
    content: @Composable () -> Unit
) {
    navController.addRoute(route)
    if (navController.currentScreen.value == route) {
        content()
    }
}

@Composable
fun NavigationHost.NavigationGraphBuilder.windowComposable(
    route: Screens,
    content: @Composable () -> Unit
) {
    navController.addRoute(route)
    if (navController.currentWindow.value == route) {
        Window(
            onCloseRequest = {
                navController.currentWindow.value = null
            },
            title = route.label,
            state = rememberWindowState(
                height = Dp.Unspecified,
                position = WindowPosition(Alignment.Center)
            ),
        ) {
            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl
            ) {
                Surface(
                    modifier = Modifier.background(color = MaterialTheme.colors.background)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
inline fun <reified VM> NavigationHost.NavigationGraphBuilder.viewModelComposable(
    route: Screens,
    crossinline content: @Composable VM.(NavController) -> Unit
) where VM : BaseViewModel {

    composable(route) {
        content(
            navController.context.getBean(VM::class.java),
            navController
        )
    }
}

@Composable
inline fun <reified VM> NavigationHost.NavigationGraphBuilder.viewModelWindowComposable(
    route: Screens,
    crossinline content: @Composable VM.(NavController) -> Unit
) where VM : BaseViewModel {

    windowComposable(route) {
        content(
            navController.context.getBean(VM::class.java),
            navController
        )
    }
}