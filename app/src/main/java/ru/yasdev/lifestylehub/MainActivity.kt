package ru.yasdev.lifestylehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.yasdev.lifestylehub.ui.theme.LifestyleHUBTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifestyleHUBTheme {
                val (selectedItem, setSelectedItem) = remember { mutableIntStateOf(0) }
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = selectedItem == 0,
                                onClick = { setSelectedItem(0) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Home,
                                        contentDescription = ""
                                    )
                                },
                                label = { Text(text = stringResource(id = R.string.главная)) }
                            )
                            NavigationBarItem(
                                selected = selectedItem == 1,
                                onClick = { setSelectedItem(1) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.DateRange,
                                        contentDescription = ""
                                    )
                                },
                                label = { Text(text = stringResource(id = R.string.мой_досуг)) }
                            )
                            NavigationBarItem(
                                selected = selectedItem == 2,
                                onClick = { setSelectedItem(2) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.AccountCircle,
                                        contentDescription = ""
                                    )
                                },
                                label = { Text(text = stringResource(id = R.string.профиль)) }
                            )
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Greeting("Android")
                    }
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LifestyleHUBTheme {
        Greeting("Android")
    }
}