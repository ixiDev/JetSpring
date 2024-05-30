package ixidev.jetspring.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.unit.dp
import com.github.lgooddatepicker.components.DatePicker
import com.github.lgooddatepicker.components.DatePickerSettings
import ixidev.jetspring.utils.isNumeric
import java.time.LocalDate
import java.util.*

/***
 * Created by ixiDev on 21/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/

@Composable
fun DateField(
    label: String,
    modifier: Modifier = Modifier,
    onDateChange: (date: LocalDate) -> Unit
) {
    val dataPicker = remember {
        DatePicker(
            DatePickerSettings(
                Locale.forLanguageTag("ar")
            )
        )
    }
    dataPicker.setDateToToday()
    dataPicker.addDateChangeListener {
        if (it.source.isValid) {
            onDateChange(it.newDate)
        }
    }
    Column(modifier) {
        Text(text = label)
        Spacer(modifier = Modifier.height(2.dp))
        SwingPanel(
            modifier = Modifier.fillMaxWidth()
                .height(40.dp),
            factory = { dataPicker },
            update = {

            }
        )
    }

}

@Composable
fun LabeledText(
    modifier: Modifier = Modifier,
    initValue: String = "",
    label: String,
    onTextChange: (text: String) -> Unit
) {
    var valueText by remember { mutableStateOf(initValue) }
    TextField(
        modifier = modifier,
        value = valueText,
        onValueChange = {
            valueText = it
            onTextChange(it)
        },
        label = {
            Text(text = label)
        },
        singleLine = true
    )
}

@Composable
fun LabeledNumericTextField(
    modifier: Modifier = Modifier,
    initValue: String = "",
    label: String,
    onTextChange: (text: String) -> Unit
) {
    var valueText by remember { mutableStateOf(initValue) }
    var error by remember { mutableStateOf(false) }
    TextField(
        modifier = modifier,
        value = valueText,
        onValueChange = {
            valueText = it
            error = !it.isNumeric()
            if (!error)
                onTextChange(it)
        },
        label = {
            Text(text = label)
        },
        isError = error,
        singleLine = true
    )

}