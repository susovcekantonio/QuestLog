package com.example.questlog.ui

import AuthViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questlog.screens.LoginScreen
import com.example.questlog.screens.RegisterScreen
import com.example.questlog.screens.GameSearchScreen
import com.example.questlog.screens.HomeScreen
import com.example.questlog.screens.TitleListScreen
import com.example.questlog.ui.theme.YourAppTheme
import com.example.questlog.viewmodel.GameSearchViewModel
import com.example.questlog.viewmodel.TitleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            val authViewModel: AuthViewModel by viewModel()
                            LoginScreen(
                                viewModel = authViewModel,
                                onNavigateToRegister = { navController.navigate("register") },
                                onLoginSuccess = { navController.navigate("home") }
                            )
                        }
                        composable("register") {
                            val authViewModel: AuthViewModel by viewModel()
                            RegisterScreen(
                                viewModel = authViewModel,
                                onNavigateToLogin = { navController.navigate("login") },
                                onRegisterSuccess = { navController.navigate("login") }
                            )
                        }
                        composable("home") { HomeScreen(navController) }
                        composable("game_search") {
                            val gameSearchViewModel: GameSearchViewModel by viewModel()
                            val titleViewModel: TitleViewModel by viewModel()
                            GameSearchScreen(
                                gameSearchViewModel = gameSearchViewModel,
                                titleViewModel = titleViewModel
                            )
                        }
                        composable("title_list") {
                            val titleViewModel: TitleViewModel by viewModel()
                            TitleListScreen(titleViewModel)
                        }
                    }
                }
            }
        }
    }
}