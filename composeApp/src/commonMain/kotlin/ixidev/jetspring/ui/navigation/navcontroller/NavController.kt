package ixidev.jetspring.ui.navigation.navcontroller


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import ixidev.jetspring.ui.navigation.Screens
import org.springframework.context.ApplicationContext

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/
class NavController(
    val context: ApplicationContext,
    private val startDestination: Screens,
    private var backStackScreens: MutableSet<Screens> = mutableSetOf(),
    private var backStackWindows: MutableSet<Screens> = mutableSetOf()
) {
    // Variable to store the state of the current screen
    var currentScreen: MutableState<Screens> = mutableStateOf(startDestination)
    var currentWindow: MutableState<Screens?> = mutableStateOf(null)

    private val routes = mutableSetOf<Screens>()

    // Function to handle the navigation between the screen
    fun navigate(route: Screens) {
        if (route !in routes) {
            throw RuntimeException("Route '$route' not exist")
        }
        if (route != currentScreen.value) {
            if (backStackScreens.contains(currentScreen.value) && currentScreen.value != startDestination) {
                backStackScreens.remove(currentScreen.value)
            }

            if (route == startDestination) {
                backStackScreens = mutableSetOf()
            } else {
                backStackScreens.add(currentScreen.value)
            }

            currentScreen.value = route
        }
    }

    fun window(route: Screens) {
        if (route !in routes) {
            throw RuntimeException("Route '$route' not exist")
        }
        if (route != currentWindow.value) {
            if (backStackWindows.contains(currentWindow.value) && currentWindow.value != null) {
                backStackWindows.remove(currentWindow.value)
            }
            currentWindow.value = route
        }
    }

    fun addRoute(route: Screens) {
        routes.add(route)
    }

    // Function to handle the back
    fun navigateBack() {
        if (backStackScreens.isNotEmpty()) {
            currentScreen.value = backStackScreens.last()
            backStackScreens.remove(currentScreen.value)
        }

    }

    fun popBackWindow() {
        currentWindow.value = null
    }
}


/**
 * Composable to remember the state of the navcontroller
 */
@Composable
fun rememberNavController(
    context: ApplicationContext,
    startDestination: Screens,
    backStackScreens: MutableSet<Screens> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(context, startDestination, backStackScreens))
}