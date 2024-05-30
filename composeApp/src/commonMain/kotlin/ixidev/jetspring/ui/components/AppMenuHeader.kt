package ixidev.jetspring.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ixidev.jetspring.utils.stringResource

/***
 * Created by ixiDev on 21/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/
@Composable
fun AppMenuHeader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Icon(
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            painter = painterResource("app-icon.svg")
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource("global.app.name"),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
