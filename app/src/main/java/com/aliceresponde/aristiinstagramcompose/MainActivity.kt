package com.aliceresponde.aristiinstagramcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.aliceresponde.aristiinstagramcompose.login.ui.LoginScreen
import com.aliceresponde.aristiinstagramcompose.ui.theme.AristiInstagramComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AristiInstagramComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    LoginScreen()
                    /*
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
                        }*/
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

