@file:OptIn(ExperimentalComposeUiApi::class)

package ixidev.jetspring.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Key
import compose.icons.fontawesomeicons.solid.User
import ixidev.jetspring.utils.stringResource

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painterResource("app-icon.svg"),
            contentDescription = "app-logo",
            modifier = Modifier.size(
                80.dp
            ).clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "JetSpring",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))

        LoginForm(viewModel)

        val error by viewModel.errorState.collectAsState()
        if (!error.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource("global.login.error"),
                fontSize = 18.sp,
                color = Color.Red
            )
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginForm(viewModel: LoginViewModel) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    TextField(
        value = userName,
        onValueChange = {
            userName = it
            viewModel.emitError(null)
        },
        singleLine = true,
        label = {
            Text(stringResource("global.username"))
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = FontAwesomeIcons.Solid.User,
                contentDescription = null
            )
        },
        modifier = Modifier.testTag("username")
    )
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        value = password,
        onValueChange = {
            password = it
            viewModel.emitError(null)
        },
        singleLine = true,
        label = {
            Text(stringResource("global.password"))
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = FontAwesomeIcons.Solid.Key,
                contentDescription = null
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.onKeyEvent {
            if (it.key == Key.Enter) {
                viewModel.login(userName, password)
                true
            } else {
                false
            }
        }.testTag("password")
    )
    Spacer(modifier = Modifier.height(8.dp))

    Button(
        modifier = Modifier.testTag("login-button"),
        onClick = {
            viewModel.login(userName, password)
        }
    ) {
        Text(text = stringResource("global.login"))
    }
}