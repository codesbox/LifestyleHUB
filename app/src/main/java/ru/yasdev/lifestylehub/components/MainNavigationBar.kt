package ru.yasdev.lifestylehub.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.yasdev.lifestylehub.R
import ru.yasdev.lifestylehub.navigation.HOME_GRAPH_ROUTE
import ru.yasdev.lifestylehub.navigation.LEISURE_GRAPH_ROUTE
import ru.yasdev.lifestylehub.navigation.PROFILE_GRAPH_ROUTE
import ru.yasdev.lifestylehub.navigation.navigateBetweenGraphs

@Composable
fun MainNavigationBar(navController: NavController) {
    NavigationBar {
        val (selectedItem, setSelectedItem) = remember { mutableIntStateOf(0) }
        NavigationBarItem(selected = selectedItem == 0, onClick = {
            setSelectedItem(0)
            navController.navigateBetweenGraphs(HOME_GRAPH_ROUTE)
        }, icon = {
            Icon(
                imageVector = Icons.Outlined.Home, contentDescription = ""
            )
        }, label = { Text(text = stringResource(id = R.string.главная)) })
        NavigationBarItem(selected = selectedItem == 1, onClick = {
            setSelectedItem(1)
            navController.navigateBetweenGraphs(LEISURE_GRAPH_ROUTE)
        }, icon = {
            Icon(
                imageVector = Icons.Outlined.DateRange, contentDescription = ""
            )
        }, label = { Text(text = stringResource(id = R.string.мой_досуг)) })
        NavigationBarItem(selected = selectedItem == 2, onClick = {
            setSelectedItem(2)
            navController.navigateBetweenGraphs(PROFILE_GRAPH_ROUTE)
        }, icon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle, contentDescription = ""
            )
        }, label = { Text(text = stringResource(id = R.string.профиль)) })
    }
}