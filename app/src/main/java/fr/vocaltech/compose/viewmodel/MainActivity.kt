package fr.vocaltech.compose.viewmodel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.vocaltech.compose.viewmodel.ui.theme.ComposeViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)

    ) {
        val context = LocalContext.current

        Button(onClick = {
            context.startActivity(Intent(context, TemperatureConverterActivity::class.java))
        }) {
            Text("Launch TemperatureConverterActivity")
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, UserLiveDataActivity::class.java))
            }
        ) {
            Text("Launch UserLivedataActivity")
        }

        Button(onClick = {
            context.startActivity(Intent(context, FlowActivity::class.java))
        }) {
            Text("Launch FlowActivity ")
        }
    }
}

@Composable
fun FunctionA() { // Unidirectional data flow
    var switchState by remember { mutableStateOf(false) }

    val onSwitchChange = { isChecked: Boolean ->
        switchState = isChecked
    }

    FunctionB(switchState, onSwitchChange)
}

@Composable
fun FunctionB(
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {
    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeViewModelTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun FunctionAPreview() {
    ComposeViewModelTheme {
        FunctionA()
    }
}