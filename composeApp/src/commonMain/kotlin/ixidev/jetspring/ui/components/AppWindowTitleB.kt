package ixidev.jetspring.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.WindowClose
import compose.icons.fontawesomeicons.regular.WindowMaximize
import compose.icons.fontawesomeicons.solid.Moon
import compose.icons.fontawesomeicons.solid.Sun
import compose.icons.fontawesomeicons.solid.UserCircle
import ixidev.jetspring.ui.GlobalViewModel
import ixidev.jetspring.ui.theme.AppThemeState

/***
 * Created by ixiDev on 20/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/

@Composable
fun WindowScope.AppWindowTitleBar(
    viewModel: GlobalViewModel,
    windowState: WindowState,
    themeState: AppThemeState,
    onClose: () -> Unit
) {
    WindowDraggableArea(
        modifier = Modifier.fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            WindowOptions(
                Modifier.align(Alignment.CenterStart),
                onClose, windowState
            )
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                DarkModeButton(themeState)
                Spacer(modifier = Modifier.width(8.dp))
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(5.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = viewModel.getUserName(),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.UserCircle,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DarkModeButton(themeState: AppThemeState) {

    Card(
        onClick = {
            themeState.isDarkTheme = !themeState.isDarkTheme
        },
        modifier = Modifier

            .fillMaxHeight()
            .aspectRatio(1f)
            .padding(5.dp),
        shape = CircleShape
    ) {
        val tint = if (themeState.isDarkTheme) Color.Yellow else Color.DarkGray
        Icon(
            imageVector = if (
                themeState.isDarkTheme
            ) FontAwesomeIcons.Solid.Sun else FontAwesomeIcons.Solid.Moon,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp),
            tint = tint,
        )
    }
//    IconButton(
//        onClick = {
//            themeState.isDarkTheme = !themeState.isDarkTheme
//        },
//        modifier = Modifier.background(
//            MaterialTheme.colors.onPrimary,
//            CircleShape
//        ).size(26.dp)
//    ) {
//
//    }
}

@Composable
private fun WindowOptions(
    modifier: Modifier,
    onClose: () -> Unit,
    windowState: WindowState
) {
    Row(modifier = modifier) {
        IconButton(
            onClick = onClose
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Regular.WindowClose,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }

        IconButton(
            onClick = {
                windowState.placement = if (windowState.placement == WindowPlacement.Floating)
                    WindowPlacement.Maximized
                else WindowPlacement.Floating
            }
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Regular.WindowMaximize,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}



