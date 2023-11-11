package com.aliceresponde.aristiinstagramcompose

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.Inside
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class SuperHero(
    val name: String,
    val realName: String,
    val producer: String,
    @DrawableRes val photo: Int
)

fun getSuperHeroes(): List<SuperHero> = listOf(
    SuperHero(
        name = "Batman",
        realName = "Bruce Wayne",
        producer = "MARVEL",
        photo = R.drawable.batman
    ),
    SuperHero(
        name = "Geopardo",
        realName = "Logan",
        producer = "DC Comics",
        photo = R.drawable.logan
    ),
    SuperHero(
        name = "SpiderMan",
        realName = "Peter Parker",
        producer = "MARVEL",
        photo = R.drawable.spiderman
    ),
    SuperHero(
        name = "WonderWoman",
        realName = "Diana Prince",
        producer = "DC Comics",
        photo = R.drawable.wonder_woman
    ),
    SuperHero(
        name = "Flash",
        realName = "Barry Allen",
        producer = "DC Comics",
        photo = R.drawable.flash
    ),
    SuperHero(
        name = "Green Lantern",
        realName = "Hal Jordan",
        producer = "DC Comics",
        photo = R.drawable.green_lantern
    ),
    SuperHero(
        name = "Thor",
        realName = "Thor",
        producer = "MARVEL",
        photo = R.drawable.thor
    )
)

/***
 * This is a sample of a Recycler View with Compose
 * Vertivcal Arrangement
 * Whith items separated by 8.dp
 * HeroItem is a Composable function that receives a SuperHero and a function onHeroClick
 */
@Composable
fun MyHeroesVerticalView() {
    val heroes = getSuperHeroes()
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(heroes) { hero ->
            HeroItem(hero, onHeroClick = {
                Toast.makeText(context, "You clicked ${hero.name}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyHeroesVerticalWithStickyHeadersByPublisherView() {
    val heroes = getSuperHeroes()
    val heroesByPublisher = heroes.groupBy { it.producer }
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        heroesByPublisher.forEach { (publisher, heroes) ->
            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = Color.White
                )
            }

            items(heroes) { hero ->
                HeroItem(hero, onHeroClick = {
                    Toast.makeText(context, "You clicked ${hero.name}", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

/**
 * Note: Avoid  nested  scrollables in the same direction
 *  scrollState is a state object that can be used to control the scrolling behavior of a LazyColumn.
 *  rememberLazyListState is a composable that can be used to remember a LazyListState.
 *  derivedStateOf is a composable that can be used to derive a state from other states.
 *  rememberCoroutineScope is a composable that can be used to remember a CoroutineScope.
 *  weight is a modifier that can be used to specify the weight of a composable, to allow see button inside the column
 *  wherethe reccycler is located
 */
@Composable
fun MyHeroesVerticalViewWithScrollControls() {
    val heroes = getSuperHeroes()
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    val showBtn by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 0 } } // optimize ui recomposition
    val coroutineScope = rememberCoroutineScope()

    Column {
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f) // allow distribute the space between the button and the recycler
        ) {
            items(heroes) { hero ->
                HeroItem(
                    hero,
                    onHeroClick = {
                        Toast.makeText(context, "You clicked ${hero.name}", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }


        if (showBtn) { // Show button if not at top (first item)
            Button(onClick = {
                coroutineScope.launch { scrollState.animateScrollToItem(0) } // Scroll to first item
            }) { Text(text = "Scroll to top") }
        }
    }
}

/***
 * This is a sample of a Recycler View with Compose
 * Horizontal Arrangement
 * Whith items separated by 8.dp
 * HeroItem is a Composable function that receives a SuperHero and a function onHeroClick
 */
@Composable
fun MyHeroesHorizontalView() {
    val heroes = getSuperHeroes()
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(heroes) { hero ->
            HeroItem(hero, onHeroClick = {
                Toast.makeText(context, "You clicked ${hero.name}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

/***
 * This is a sample of a Recycler View with Compose
 * Horizontal Arrangement
 * Whith items separated by 8.dp
 * HeroItem is a Composable function that receives a SuperHero and a function onHeroClick
 */
@Composable
fun MyHeroesVerticalGridView() {
    val heroes = getSuperHeroes()
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(heroes) { hero ->
                HeroItem(hero, onHeroClick = {
                    Toast.makeText(context, "You clicked ${hero.name}", Toast.LENGTH_SHORT).show()
                })
            }
        },
        contentPadding = PaddingValues(8.dp)
    )
}

@Composable
fun HeroItem(hero: SuperHero, onHeroClick: (SuperHero) -> Unit = {}) {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier
            .background(Color.Black)
            .border(1.dp, Color.Yellow, RectangleShape)
            .size(200.dp)
            .clickable { onHeroClick(hero) }
    ) {
        Column {
            Image(
                painter = painterResource(id = hero.photo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = Inside,

                )
            Text(text = hero.name, Modifier.align(Alignment.CenterHorizontally), color = Color.White)
            Text(text = hero.realName, Modifier.align(Alignment.CenterHorizontally), color = Color.White)
            Text(
                text = hero.producer,
                Modifier.align(Alignment.End).padding(end = 8.dp, top = 8.dp),
                color = Color.White
            )
        }
    }
}