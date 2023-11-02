package fr.vocaltech.compose.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.vocaltech.compose.viewmodel.ui.theme.ComposeViewModelTheme

class UserLiveDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserScreenSetup()
                }
            }
        }
    }
}

@Composable
fun UserScreenSetup(viewModel: UserLiveDataModel = viewModel()) {
    val currentUser by viewModel.currentUser.observeAsState(User("", ""))

    val setUser = { u: User ->
        viewModel.setUser(u)
    }

    UserMainScreen(
        currentUser,
        setUser
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMainScreen(
    currentUser: User,
    setUser: (User) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Text(
            text = "Current User login: ${currentUser.login}"
        )

        TextField(
            value = currentUser.login,
            onValueChange = {
                setUser(User(it, ""))
            },
            singleLine = true
        )
    }

}

@Preview(showBackground = true)
@Composable
fun UserMainScreenPreview() {
    ComposeViewModelTheme {
        UserScreenSetup()
    }
}