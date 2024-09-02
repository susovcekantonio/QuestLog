
package com.example.questlog.screens

import AuthViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.questlog.R
import com.example.questlog.ui.theme.OpenSans

@Composable
fun RegisterScreen(viewModel: AuthViewModel = viewModel(), onNavigateToLogin: () -> Unit, onRegisterSuccess: () -> Unit) {
    val backgroundImage = painterResource(id = R.drawable.joystick)
    BackgroundScreen(backgroundImage = backgroundImage) {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var isRegistrationSuccessful by remember { mutableStateOf(false) }


        LaunchedEffect(isRegistrationSuccessful) {
            if (isRegistrationSuccessful) {
                onNavigateToLogin()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Quest Log",
                fontSize = 64.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Register", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                errorMessage = when {
                    email.isBlank() -> "Email cannot be empty"
                    password.isBlank() -> "Password cannot be empty"
                    else -> null
                }
                errorMessage?.let {
                    return@Button
                }
                viewModel.register(email, password, onRegisterSuccess) { message ->
                    if (message == null) {
                        isRegistrationSuccessful = true
                    } else {
                        errorMessage = message
                    }
                }
            }) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onNavigateToLogin) {
                Text("Go to Login")
            }

            errorMessage?.let { message ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
