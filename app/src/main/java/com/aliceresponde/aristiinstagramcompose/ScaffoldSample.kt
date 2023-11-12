package com.aliceresponde.aristiinstagramcompose

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyScaffoldSample() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { snackbarHostState },
        topBar = {
            MyTopBar(
                onItemClicked = {
                    //Toast.makeText(context, "Toast: Item $it", Toast.LENGTH_SHORT).show()
                    // TODO check it
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("SNACK: item $it ")
                    }
                }
            )
        },
        bottomBar = { MyBottomNavigation() },


    ) {

        Button(onClick = { /*TODO*/ }) { Text("Click me") }

    }
}

@Composable
fun MyBottomNavigation() {
    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = Color.White,
        elevation = 4.dp,
    ) {
        var selectedItem by rememberSaveable { mutableIntStateOf(0) }
        BottomNavigation(
            contentColor = Color.White,
            backgroundColor = Color.Gray,
            elevation = 4.dp,
        ) {
            BottomNavigationItem(
                selected = selectedItem == 0,
                onClick = {selectedItem = 0},
                unselectedContentColor = Color.LightGray,
                selectedContentColor = Color.Red,
                icon = {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
                }, label = { Text("Favorite") }
            )

            BottomNavigationItem(
                selected = selectedItem == 1,
                onClick = { selectedItem = 1},
                unselectedContentColor = Color.LightGray,
                selectedContentColor = Color.White,
                icon = {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }, label = { Text("Home") }
            )

            BottomNavigationItem(
                selected = selectedItem == 2,
                onClick = { selectedItem = 2},
                unselectedContentColor = Color.LightGray,
                selectedContentColor = Color.White,
                icon = {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                }, label = { Text("Settings") }
            )

        }
    }
}

@Composable
fun MyTopBar(onItemClicked: (String) -> Unit) {
    TopAppBar(
        title = { Text("My toolbar  title") },
        navigationIcon = {
            // Icon a la izquierda arriba
            IconButton(onClick = { onItemClicked("Back") }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            // Iconos a la derecha
            IconButton(onClick = { onItemClicked("Search") }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            }
            IconButton(onClick = { onItemClicked("Info") }) { Icon(Icons.Filled.Info, contentDescription = "Info") }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White,
    )
}