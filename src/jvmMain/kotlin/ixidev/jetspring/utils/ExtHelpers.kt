package ixidev.jetspring.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ixidev.jetspring.base.BaseViewModel
import org.springframework.context.ApplicationContext
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.logging.Logger


/***
 * Created by ixiDev on 20/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/
private fun ResourceBundle.valueOf(code: String): String {
    return if (containsKey(code)) {
        getString(code)
    } else {
        code
    }
}


inline fun <reified T : BaseViewModel> ApplicationContext.viewModel(): T {
    return getBean(T::class.java)
}

val LocalApplicationContext = staticCompositionLocalOf<ApplicationContext> {
    error("ApplicationContext not provided")
}

val LocalResourceBundle = staticCompositionLocalOf<ResourceBundle> {
    error("ResourceBundle not provided")
}

val LocalLogger = staticCompositionLocalOf<Logger> {
    error("ResourceBundle not provided")
}

@Composable
fun stringResource(key: String): String {
    return LocalResourceBundle.current.valueOf(key)
}

fun String.isNumeric() = all { it in '0'..'9' }


fun LocalDate.toDate(): Date {
    return Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())
}