package dev.toothlonely.vkeducation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppInfoScreen(
    applicationId: Int
) {
    val application = STUB.getAppById(applicationId)
    Text(text = application?.name.toString())
}