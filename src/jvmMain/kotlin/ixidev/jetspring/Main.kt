package ixidev.jetspring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ixidev.jetspring.security.UserAuthSate
import ixidev.jetspring.ui.auth.LoginScreen
import ixidev.jetspring.ui.components.AppWindowTitleBar
import ixidev.jetspring.ui.components.SideBarMenu
import ixidev.jetspring.ui.home.HomeScreen
import ixidev.jetspring.ui.home.HomeViewModel
import ixidev.jetspring.ui.navigation.Screens
import ixidev.jetspring.ui.navigation.navcontroller.*
import ixidev.jetspring.ui.theme.AppThemeState
import ixidev.jetspring.ui.theme.JetSpringAppTheme
import ixidev.jetspring.ui.theme.rememberAppThemeState
import ixidev.jetspring.utils.*
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import java.util.logging.Logger


@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@AutoConfiguration
@EnableGlobalAuthentication
class Main {
    init {
        System.setProperty(
            SecurityContextHolder.SYSTEM_PROPERTY,
            SecurityContextHolder.MODE_GLOBAL
        )
    }
}

fun main(args: Array<String>) = application {
    val logger = Logger.getLogger(Main::class.simpleName)
    val applicationContext = runApplication<Main>(*args)
    val resourceBundle = ResourceBundle.getBundle(
        "strings",
        Locale.forLanguageTag("en") // or Locale.forLanguageTag("ar")
    )
    val authSate = remember { applicationContext.getBean(UserAuthSate::class.java) }
    val auth by authSate.auth.collectAsState(false)
    val themeState = rememberAppThemeState()
    CompositionLocalProvider(
        LocalApplicationContext provides applicationContext,
        LocalResourceBundle provides resourceBundle,
        LocalLogger provides logger
    ) {
        JetSpringAppTheme(darkTheme = themeState.isDarkTheme) {
            if (auth) {
                AppMainWindow(LocalApplicationContext.current, themeState)
            } else {
                AppLoginWindow(LocalApplicationContext.current)
            }
        }
    }

}

@Composable
private fun ApplicationScope.AppLoginWindow(applicationContext: ApplicationContext) {
    val loginWindowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        width = 400.dp,
        height = 600.dp,
    )
    Window(
        onCloseRequest = {
            SpringApplication.exit(applicationContext)
            exitApplication()
        },
        state = loginWindowState,
        resizable = false,
        title = stringResource("global.login"),
        icon = painterResource("app-icon.svg")
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Ltr
        ) {
            LoginScreen(applicationContext.viewModel())
        }
    }
}

@Composable
private fun ApplicationScope.AppMainWindow(
    applicationContext: ApplicationContext,
    themeState: AppThemeState
) {
    val globalWindowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        width = 1400.dp,
        height = 800.dp,
    )
    Window(
        onCloseRequest = {
            SpringApplication.exit(applicationContext)
            exitApplication()
        },
        state = globalWindowState,
        undecorated = true,
        icon = painterResource("app-icon.svg")
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Ltr
        ) {
            Surface(
                modifier = Modifier.background(color = MaterialTheme.colors.background)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    AppWindowTitleBar(
                        applicationContext.viewModel(),
                        globalWindowState,
                        themeState
                    ) {
                        exitApplication()
                    }
                    AppMainContainer(applicationContext)
                }
            }
        }
    }
}


@Composable
fun AppMainContainer(context: ApplicationContext) {
    val navController by rememberNavController(context, Screens.Home)
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        SideBarMenu(
            modifier = Modifier
                .weight(0.15f),
            navController
        )
        Box(
            modifier = Modifier.fillMaxHeight()
                .weight(0.85f)
        ) {
            AppNavigationHost(navController = navController)
        }
    }
}


@Composable
fun AppNavigationHost(navController: NavController) {
    NavigationHost(
        navController,
        windowScreens = {
            // add window navigation here
            //viewModelWindowComposable<>()
            windowComposable(Screens.NewWindow) {
                Text("New Window")
            }
        }
    ) {
        viewModelComposable<HomeViewModel>(Screens.Home) {
            HomeScreen(this, navController)
        }
        composable(Screens.Settings) {
            Text("Settings")
        }
    }.build()
}



