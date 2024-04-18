package com.valentinerutto.roomdbtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.valentinerutto.roomdbtutorial.ui.LineItemComposable
import com.valentinerutto.roomdbtutorial.ui.LineViewModel
import com.valentinerutto.roomdbtutorial.ui.theme.RoomDBTutorialTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewmodel by viewModel<LineViewModel>()

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            viewmodel.getRandomLine()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDBTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    Greeting("Android")

                    viewmodel.getSavedLine()
                  
                    val lineSaved = viewmodel.lineList.collectAsState().value[0]
                    LineItemComposable(Modifier.fillMaxSize(), lineSaved)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDBTutorialTheme {
        Greeting("Android")
    }
}