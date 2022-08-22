package ixidev.jetspring.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

val fontFamily = FontFamily(
    Font(
        resource = "Almarai-Regular.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)
val Typography = Typography(
    defaultFontFamily = fontFamily,
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
)