package ixidev.jetspring.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ExternalLinkAlt

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/


sealed class Screens(
    val label: String,
    val icon: ImageVector? = null
) {
    object Home : Screens(
        label = "global.label.home",
        icon = Icons.Rounded.Home
    )

    object Settings : Screens(
        label = "global.label.settings",
        icon = Icons.Rounded.Settings
    )

    object NewWindow : Screens(
        label = "global.label.newWindow",
        icon = FontAwesomeIcons.Solid.ExternalLinkAlt
    )

}