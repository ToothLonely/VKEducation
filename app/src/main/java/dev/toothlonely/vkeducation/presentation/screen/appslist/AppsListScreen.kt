package dev.toothlonely.vkeducation.presentation.screen.appslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.toothlonely.vkeducation.data.STUB
import dev.toothlonely.vkeducation.presentation.navigation.Screen

@Composable
fun AppsListScreen(modifier: Modifier, onNavigateTo: (Screen) -> Unit = {}) {

    val apps = STUB.getAllApps()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff2c71f4))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(110.dp)
        ) {
            RuStoreLabel()
            FourSquaresIcon()
        }

        LazyColumn(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
                .background(color = Color.White)
        ) {
            itemsIndexed(apps) { index, appItem ->
                AppItem(
                    application = appItem,
                    modifier = Modifier
                        .clickable {
                            onNavigateTo(Screen.AppDetail(applicationName = appItem.name))
                        }
                )

                if (index < apps.size) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppsListScreenPreview() {
    AppsListScreen(modifier = Modifier)
}