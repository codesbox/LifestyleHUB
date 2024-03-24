package ru.yasdev.lifestylehub.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.yasdev.lifestylehub.components.MainNavigationBar
import ru.yasdev.lifestylehub.navigation.BottomBarNavGraph
import ru.yasdev.lifestylehub.ui.theme.LifestyleHUBTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifestyleHUBTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    MainNavigationBar(navController = navController)
                }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            BottomBarNavGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }
}



