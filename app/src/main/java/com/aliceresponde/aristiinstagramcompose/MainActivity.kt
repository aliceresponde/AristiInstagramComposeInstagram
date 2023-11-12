package com.aliceresponde.aristiinstagramcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aliceresponde.aristiinstagramcompose.Routes.FavScreen
import com.aliceresponde.aristiinstagramcompose.Routes.HomeScreen
import com.aliceresponde.aristiinstagramcompose.Routes.SettingsScreen
import com.aliceresponde.aristiinstagramcompose.ui.theme.AristiInstagramComposeTheme
import com.aliceresponde.aristiinstagramcompose.ui.theme.TwitterBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AristiInstagramComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = TwitterBackground) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = FavScreen.route) {
                        composable(FavScreen.route) { FavScreen(navController = navController) }
                        composable(HomeScreen.route) { HomeScreen(navController = navController) }
                        composable(SettingsScreen.route) { SettingsScreen(navController = navController) }
                        composable(
                            route = Routes.Screen4.route, // Screen4/{age}
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(navController = navController, backStackEntry.arguments?.getInt("age") ?: 0)
                        }
                        composable(
                            route = Routes.Screen5.route,
                            arguments = listOf(navArgument("name") { type = NavType.StringType })
                        ) { backStackEntry ->
                            Screen5(
                                navController = navController,
                                backStackEntry.arguments?.getString("name") ?: "pepe"
                            )
                        }
                        //MyScaffoldSample()
                        //MyHeroesVerticalWithStickyHeadersByPublisherView()
                        //MyHeroesVerticalViewWithScrollControls()
                        //MyHeroesHorizontalView()
                        //MyHeroesVerticalGridView()
                        //TwitterScreen()
                    }
                }
            }
        }
    }
}
