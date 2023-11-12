package com.aliceresponde.aristiinstagramcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliceresponde.aristiinstagramcompose.Routes.HomeScreen
import com.aliceresponde.aristiinstagramcompose.Routes.SettingsScreen

const val FAV_SCREEN = "FAV_SCREEN"
const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
const val HOME_SCREEN = "HOME_SCREEN"

sealed class Routes(val route: String) {
    object FavScreen : Routes(FAV_SCREEN)
    object HomeScreen : Routes(HOME_SCREEN)
    object SettingsScreen : Routes(SETTINGS_SCREEN)

    object Screen4 : Routes("Screen4/{age}") {
        fun createRoute(age: Int) = "Screen4/$age"
    }

    // rout uith optional parameter name
    object Screen5 : Routes("Screen4?name{name}") {
        fun createRoute(name: String = "Default") = "Screen4?name=$name"
    }
}

@Composable
fun FavScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red),
        contentAlignment = Alignment.Center,
    ) {
        TextButton(
            onClick = { navController.navigate(HomeScreen.route) }
        ) { Text(text = "FAV GO to HOME SCREEN", modifier = Modifier.background(color = Color.Yellow).padding(16.dp)) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray),
        contentAlignment = Alignment.Center,
    ) {
        TextButton(
            onClick = { navController.navigate(SettingsScreen.route) }
        ) {
            Text(
                text = "Home GO to SETTINGS SCREEN",
                modifier = Modifier.background(color = Color.Yellow).padding(16.dp)
            )
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue),
        contentAlignment = Alignment.Center,
    ) {
        TextButton(
            // route   "Screen4/10"
            onClick = { navController.navigate(Routes.Screen4.createRoute(10)) }
        ) {
            Text(
                text = "You are in settings screen, go  to Screen 4 with parameter 10",
                modifier = Modifier.background(color = Color.Yellow).padding(16.dp)
            )
        }
    }
}

@Composable
fun Screen4(navController: NavController, age: Int) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        TextButton(
            onClick = { navController.navigate(Routes.Screen5.createRoute()) }
        )
        {
            Text(
                text = "You are in Screen 4, parameter value is age : $age",
                modifier = Modifier.background(color = Color.Yellow).padding(16.dp)
            )
        }
    }
}

@Composable
fun Screen5(navController: NavController, name: String) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Green),
        contentAlignment = Alignment.Center,
    ) {
        TextButton(onClick = { })
        {
            Text(
                text = "You are in Screen 5, parameter value is Name : $name",
                modifier = Modifier.background(color = Color.Yellow).padding(16.dp)
            )
        }
    }
}