package fr.vocaltech.compose.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.vocaltech.compose.viewmodel.ui.theme.ComposeViewModelTheme

class TemperatureConverterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenSetup()
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: TemperatureConverterModel = viewModel()) {
    MainScreen(
        viewModel.isFahrenheit,
        viewModel.result,
        switchChange = { viewModel.switchChange() },
        convertTemp = { viewModel.convertTemp(it) }
    )
}

@Composable
fun MainScreen(
    isFahrenheit: Boolean,
    result: String,
    switchChange: () -> Unit,
    convertTemp: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var textState by remember { mutableStateOf("") }

        val onTextChange = { curText: String ->
            textState = curText
        }

        Text(
            text = "Temperature Converter",
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(24F, TextUnitType.Sp)
        )

        InputRow(
            isFahrenheit = isFahrenheit,
            textState = textState,
            switchChange = switchChange,
            onTextChange = onTextChange
        )

        Text(text = "Result: $result")

        Button(onClick = {
            convertTemp(textState)
        }) {
            Text(text = "Convert")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputRow(
    isFahrenheit: Boolean,
    textState: String,
    switchChange: () -> Unit,
    onTextChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            modifier = Modifier.scale(0.8f),
            checked = isFahrenheit,
            onCheckedChange = { switchChange() }
        )

        OutlinedTextField(
            value = textState,
            onValueChange = onTextChange,
            singleLine = true,
            label = { Text(text = "Enter temperature") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .padding(8.dp)
                .width(240.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp)
        )

        Crossfade(
            targetState = isFahrenheit,
            label = "",
            animationSpec = tween(1000)
        ) { visible ->

            when(visible) {
                true -> Text(
                    text = "\u2109",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                false -> Text(
                    text = "\u2103",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenTemperaturePreview() {
    ComposeViewModelTheme {
        ScreenSetup()
    }
}