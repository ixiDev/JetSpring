package ixidev.jetspring.ui.components

import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.VisualTransformation

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/


@Composable
fun ValidatorTextField(
    value: String,
    onValid: (value: String) -> Unit,
    singleLine: Boolean,
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    visualTransformation: VisualTransformation? = null,

    ) {

    var error by remember { mutableStateOf(false) }
    if (visualTransformation == null)
        TextField(
            value = value,
            onValueChange = {

            },
            singleLine = singleLine,
            label = label,
            leadingIcon = leadingIcon,
            isError = error
        )
    else {
        TextField(
            value = value,
            onValueChange = onValid,
            singleLine = singleLine,
            label = label,
            leadingIcon = leadingIcon,
            visualTransformation = visualTransformation
        )
    }
}