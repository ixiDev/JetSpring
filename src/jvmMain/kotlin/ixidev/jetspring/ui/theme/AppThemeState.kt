package ixidev.jetspring.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 ** Project    : JetIPTV
 ** Created By : Abdelmajid ID ALI
 ** On         : 26/04/2022
 ** Email      : feedback.mrzero@gmail.com
 **/
class AppThemeState {
    private val themeState: MutableState<Boolean> = mutableStateOf(false)
    var isDarkTheme: Boolean = false
        get() = themeState.value
        set(value) {
            field = value
            themeState.value = field
        }
}

@Composable
fun rememberAppThemeState(): AppThemeState = remember {
    AppThemeState()
}