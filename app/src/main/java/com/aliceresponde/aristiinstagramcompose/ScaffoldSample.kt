package com.aliceresponde.aristiinstagramcompose

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyScaffoldSample() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    //val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        snackbarHost = { scaffoldState },
        topBar = {
            MyTopBar(
                onItemClicked = {
                    Toast.makeText(context, "Toast: Item $it", Toast.LENGTH_SHORT).show()
                    // TODO check it shou action in toast
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("SNACK: item $it ")
                    }
                },
                onClickDrawer = {
                    coroutineScope.launch {// open
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        bottomBar = { MyBottomNavigation() },
        floatingActionButton = { MyFAB() },
        floatingActionButtonPosition = FabPosition.Center,  // Default right,
        isFloatingActionButtonDocked = false, // if its true  put half in bottom bar,
        drawerContent = {
            MyDrawer(onCloseDrawer = { //  close drawer
                coroutineScope.launch { scaffoldState.drawerState.close() }
            })
        },
        drawerGesturesEnabled = true,

        ) {

        Button(onClick = { /*TODO*/ }) { Text("Click me") }
    }
}

@Composable
fun MyDrawer(
    onCloseDrawer: () -> Unit = {}
) {
    Column(Modifier.padding(8.dp)) {
        Text(text = "Item 1", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onCloseDrawer() })
        Text(text = "Item 2", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onCloseDrawer() })
        Text(text = "Item 3", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onCloseDrawer() })
        Text(text = "Item 4", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onCloseDrawer() })
        Text(text = "Item 5", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onCloseDrawer() })
    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = Color.Magenta,
        contentColor = Color.White,
    ) { Icon(imageVector = Icons.Filled.Add, contentDescription = "Favorite") }
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
                onClick = { selectedItem = 0 },
                unselectedContentColor = Color.LightGray,
                selectedContentColor = Color.Red,
                icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite") },
                label = { Text("Favorite") }
            )

            BottomNavigationItem(
                selected = selectedItem == 1,
                onClick = { selectedItem = 1 },
                unselectedContentColor = Color.LightGray,
                selectedContentColor = Color.White,
                icon = {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }, label = { Text("Home") }
            )

            BottomNavigationItem(
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 },
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
fun MyTopBar(onItemClicked: (String) -> Unit, onClickDrawer: () -> Unit = {}) {
    TopAppBar(
        title = { Text("My toolbar  title") },
        navigationIcon = {
            // Icon a la izquierda arriba
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu"
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