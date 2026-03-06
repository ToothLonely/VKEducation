package dev.toothlonely.vkeducation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.text.isDigitsOnly
import dev.toothlonely.vkeducation.ui.theme.VKEducationTheme

@Composable
fun FirstScreen(modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val phoneNumber = rememberTextFieldState()

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(5.dp)
                )
                .height(250.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = {
                    Text(text = stringResource(R.string.edit_text_placeholder))
                }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        openSecondActivity(context, text)
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Открыть вторую Activity")
                }

                Button(
                    onClick = { shareText(context, text) },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Поделиться через...")
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(5.dp)
                )
                .height(250.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                state = phoneNumber,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                inputTransformation = InputTransformation.maxLength(10).then {
                    if (!asCharSequence().isDigitsOnly()) revertAllChanges()
                },
                outputTransformation = OutputTransformation {
                    if (length > 0) insert(0, "(")
                    if (length > 4) insert(4, ")")
                    if (length > 8) insert(8, "-")
                },
                prefix = { Text("+7") },
                label = {
                    Text("Введите номер телефона")
                }
            )

            Button(
                onClick = {
                    openPhoneCall(context, phoneNumber.text as String)
                }
            ) {
                Text("Позвонить другу")
            }
        }

    }
}

private fun openSecondActivity(context: Context, message: String) {
    val intent = Intent(context, SecondActivity::class.java).apply {
        putExtra("secondActivityMessage", message)
    }
    context.startActivity(intent)
}

private fun openPhoneCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phoneNumber".toUri()
    }
    context.startActivity(intent)
}

private fun shareText(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    VKEducationTheme {
        FirstScreen()
    }
}