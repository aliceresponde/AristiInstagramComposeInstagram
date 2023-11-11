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
import com.aliceresponde.aristiinstagramcompose.ui.theme.AristiInstagramComposeTheme
import com.aliceresponde.aristiinstagramcompose.ui.theme.TwitterBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AristiInstagramComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = TwitterBackground) {
                    MyHeroesVerticalWithStickyHeadersByPublisherView()
                //MyHeroesVerticalViewWithScrollControls()
                //MyHeroesHorizontalView()
                    //MyHeroesVerticalGridView()
                    //TwitterScreen()
                }
            }
        }
    }
}
