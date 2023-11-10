package com.aliceresponde.aristiinstagramcompose

import android.widget.ToggleButton
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliceresponde.aristiinstagramcompose.ui.theme.TwitterBackground

@Preview(showBackground = true)
@Composable
fun TwitterScreen() {
    var messageCounter by rememberSaveable { mutableIntStateOf(0) }
    var messageEnabled by rememberSaveable { mutableStateOf(false) }
    var favouriteCounter by rememberSaveable { mutableIntStateOf(1) }
    var favouriteEnabled by rememberSaveable { mutableStateOf(false) }
    var retweetCounter by rememberSaveable { mutableIntStateOf(1) }
    var retweetEnabled by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.padding(16.dp)) {
        Row {
            ProfileImage(profileImg = R.drawable.profile)
            Column(Modifier.fillMaxWidth()) {
                HeaderTwitter("Aristi", "@aristi", "1h")
                Text(
                    text = "Lorem ipsum dolor sit amet, consecte" +
                        "sdfsfsefjlsejflsefjlisejfisfiksdhnfkijsnfkj" +
                        "sdfsfsefjlsejflsefjlisejfisfiksdhnfkijsnfkj" +
                        "sdfsfsefjlsejflsefjlisejfisfiksdhnfkijsnfkj"+
                        "sdfsfsefjlsejflsefjlisejfisfiksdhnfkijsnfkj" +
                        "sbfjksbfbsjkfbsfbsjhdfbsebfbfsb",
                    fontSize = 19.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TwitterImage(R.drawable.profile)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    TwitterToggleButton(
                        counter = messageCounter,
                        defaultImage = R.drawable.ic_chat,
                        otherImage = R.drawable.ic_chat_filled,
                        defaultColor = Color.LightGray,
                        otherColor = Color.LightGray,
                        isChecked = messageEnabled,
                        onCheckedChange = {
                            messageEnabled = it
                            if (it) messageCounter += 1 else messageCounter -= 1
                        }
                    )

                    TwitterToggleButton(
                        counter = retweetCounter,
                        defaultImage = R.drawable.ic_rt,
                        otherImage = R.drawable.ic_rt,
                        defaultColor = Color.LightGray,
                        otherColor = Color.Green,
                        isChecked = retweetEnabled,
                        onCheckedChange = {
                            retweetEnabled = it
                            if (it) retweetCounter += 1 else retweetCounter -= 1
                        }
                    )

                    TwitterToggleButton(
                        counter = favouriteCounter,
                        defaultImage = R.drawable.ic_like,
                        otherImage = R.drawable.ic_like_filled,
                        defaultColor = Color.LightGray,
                        otherColor = Color.Red,
                        isChecked = favouriteEnabled,
                        onCheckedChange = {
                            favouriteEnabled = it
                            if (it) favouriteCounter += 1 else favouriteCounter -= 1
                        }
                    )
                }
            }
        }
        Divider(Modifier.height(1.dp), color = Color.LightGray)
    }
}

@Composable
fun TwitterToggleButton(
    counter: Int,
    @DrawableRes defaultImage: Int,
    @DrawableRes otherImage: Int,
    defaultColor: Color,
    otherColor: Color,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    var isIconSelected by rememberSaveable { mutableStateOf(isChecked) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        IconToggleButton(
            checked = isChecked, onCheckedChange = {
                onCheckedChange(it)
                isIconSelected = it
            }
        ) {
            Icon(
                painter = painterResource(id = if (isIconSelected) otherImage else defaultImage),
                contentDescription = "twitter image",
                tint = if (isIconSelected) otherColor else defaultColor,
                modifier = Modifier.size(24.dp),
            )
        }

        Text(text = counter.toString(), color = Color.LightGray)
    }
}

@Composable
fun TwitterImage(@DrawableRes imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "twitter image",
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(8.dp).clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun HeaderTwitter(username: String, nickname: String, time: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = username,
            fontWeight = Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = nickname, fontSize = 20.sp, color = Color.LightGray, modifier = Modifier.padding(end = 8.dp))
        Text(text = time, fontSize = 20.sp, color = Color.LightGray)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painterResource(R.drawable.ic_dots),
            contentDescription = "dots",
            tint = Color.White,
        )
    }
}

@Composable
fun ProfileImage(@DrawableRes profileImg: Int) {
    Image(
        painter = painterResource(id = profileImg),
        contentDescription = null,
        modifier = Modifier.size(55.dp).padding(8.dp).clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}

