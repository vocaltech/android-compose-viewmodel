package fr.vocaltech.compose.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.vocaltech.compose.viewmodel.ui.theme.ComposeViewModelTheme

class FlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlowSetup()
                }
            }
        }
    }
}

@Composable
fun FlowSetup(model: FlowModel = viewModel()) {
    //val count by model.myFlow.collectAsState(initial = "Current value = ")
    var count by remember { mutableStateOf("Current value = ") }

    val stateFlow by model.stateFlow.collectAsState()
    val increaseValue = {
        model.increaseValue()
    }

    LaunchedEffect(Unit) {
        try {
            model.myFlow
                .collect {
                    count = "Current count = $it"
                }
        } finally {
            count = "Flow stream ended !"
        }
    }

    FlowScreen(
        count,
        stateFlow,
        increaseValue
    )
}

@Composable
fun FlowScreen(
    count: String,
    stateFlow: Int,
    increaseValue: () -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Text(text = count)

        Text(text = stateFlow.toString())

        Button(onClick = increaseValue) {
            Text(text = "Increase value")
        }
    }
}