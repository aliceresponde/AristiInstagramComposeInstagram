package com.aliceresponde.aristiinstagramcompose.login.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliceresponde.aristiinstagramcompose.R
import com.aliceresponde.aristiinstagramcompose.ui.theme.BlueFB

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    if (!isLoading) {
        Box(Modifier.fillMaxSize().padding(8.dp)) {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), viewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    } else
        FullScreenCircleLoading(Modifier.fillMaxSize())
}

@Composable
private fun FullScreenCircleLoading(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).size(50.dp)) }
}

@Composable
fun Footer(modifier: Modifier, onClick: () -> Unit = {}) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(color = Color.LightGray, modifier = Modifier.height(1.dp))
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        SingUp(onClick)
    }
}

@Composable
private fun SingUp(onClick: () -> Unit = {}) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = "Don't have an account?",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Sign up.",
            color = BlueFB,
            fontWeight = Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Filled.Close,
        contentDescription = "Close",
        modifier = modifier.clickable { activity.finish() }
    )
}

@Composable
fun Body(modifier: Modifier, viewModel: LoginViewModel) {
    //var email by rememberSaveable { mutableStateOf("") }
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val isLoginEnabled: Boolean by viewModel.isEmailAndPasswordValid.observeAsState(false)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageInstagram(modifier = Modifier.padding(bottom = 16.dp))
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        EmailField(email) { viewModel.onLoginChange(email = it, password = password) }
        Spacer(modifier = Modifier.padding(bottom = 4.dp))
        PasswordField(password) { viewModel.onLoginChange(email, password = it) }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        ForgotPassword(modifier = Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        LoginButton(modifier = Modifier.align(Alignment.End), isEnabled = isLoginEnabled, viewModel)
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        OrDivider(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(bottom = 32.dp))
        LoginWithFacebook(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun ImageInstagram(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.instagram_img),
        contentDescription = "Instagram",
        modifier = modifier
    )
}

@Composable
fun LoginWithFacebook(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.facebook_icon),
            contentDescription = "Facebook",
            modifier = modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(
            text = "Log in with Facebook",
            color = BlueFB,
            fontWeight = Bold
        )

    }
}

@Composable
fun OrDivider(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(color = Color.LightGray, modifier = Modifier.weight(1f))
        Text(
            text = "OR",
            Modifier.padding(horizontal = 8.dp),
            color = Color.LightGray
        )
        Divider(color = Color.LightGray, modifier = Modifier.weight(1f))
    }
}

@Composable
fun LoginButton(modifier: Modifier, isEnabled: Boolean, viewModel: LoginViewModel) {
    Button(
        onClick = { viewModel.onDoLogin() },
        modifier = modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xffa7bee8),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = isEnabled
    ) {
        Text(text = "Log in", color = Color.White, fontSize = 22.sp)
    }
}

@Composable
fun ForgotPassword(modifier: Modifier, onClick: () -> Unit = {}) {
    Text(
        text = "Forgot password?",
        color = Color(0xFF4EA8E9),
        fontWeight = Bold,

        modifier = modifier.clickable { onClick() }
    )
}

@Composable
fun PasswordField(password: String, onValueChange: (String) -> Unit) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(true) }
    TextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Password") },
        textStyle = TextStyle(color = Color.Gray),
        placeholder = { Text(text = "Type your Password") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedLabelColor = BlueFB,
            unfocusedLabelColor = BlueFB,
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val image = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = image,
                    contentDescription = "Password visibility",
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun EmailField(email: String, onValueChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Type your email") },
        keyboardOptions = KeyboardOptions(keyboardType = Email),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.Gray),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color(0xFF4EA8E9),
            unfocusedLabelColor = Color(0xFF4EA8E9),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

