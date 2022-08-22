package ixidev.jetspring.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CashRegister
import compose.icons.fontawesomeicons.solid.PaperPlane
import compose.icons.fontawesomeicons.solid.Paperclip
import compose.icons.fontawesomeicons.solid.Users
import ixidev.jetspring.ui.navigation.navcontroller.NavController

/***
 * Created by ixiDev on 18/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {

    Row(modifier = Modifier.fillMaxWidth()) {
        CategoryView(
            modifier = Modifier.weight(1f),
            label = "الفواتير",
            icon = FontAwesomeIcons.Solid.Paperclip
        )
        CategoryView(
            modifier = Modifier.weight(1f),
            label = "الإشتراكات",
            icon = FontAwesomeIcons.Solid.PaperPlane
        )
        CategoryView(
            modifier = Modifier.weight(1f),
            label = "المصروفات",
            icon = FontAwesomeIcons.Solid.CashRegister
        )

        CategoryView(
            modifier = Modifier.weight(1f),
            label = "الموضفين",
            icon = FontAwesomeIcons.Solid.Users
        )

    }
}


@Composable
fun CategoryView(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector
) {
    Card(
        modifier = modifier
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(10.dp),
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
                .height(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = label,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}