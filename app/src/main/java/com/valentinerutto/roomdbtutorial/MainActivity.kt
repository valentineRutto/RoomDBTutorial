package com.valentinerutto.roomdbtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.ui.CategoryChipCompossable
import com.valentinerutto.roomdbtutorial.ui.LineViewModel
import com.valentinerutto.roomdbtutorial.ui.MainView
import com.valentinerutto.roomdbtutorial.ui.theme.RoomDBTutorialTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewmodel by viewModel<LineViewModel>()

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            viewmodel.getListofPickUpLines()
        }

    }

    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDBTutorialTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val lineSaved = viewmodel.stateFlow.collectAsState().value.lines

                    val listSorted = remember {
                        mutableStateListOf<PickuplineEntity>()
                    }

                    if (lineSaved != null) {
                        listSorted.addAll(lineSaved)
                    }
                    var pageSize = remember {
                        listSorted.size
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {

                        Text(text = "Pickup Lines")


                        if (lineSaved != null) {

                            CategoryChipCompossable(
                                lineSaved,
                                onSelectedChanged = { categorySelected ->
                                    listSorted.clear()
                                    listSorted.addAll(lineSaved.filter { it.category == categorySelected })
                               pageSize = listSorted.size
                                })

                            MainView(lines = listSorted, pageSize = pageSize)
                        }
                    }

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