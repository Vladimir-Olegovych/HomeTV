package com.gigcreator.hometv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gigcreator.hometv.screens.main.MainScreen
import com.gigcreator.hometv.screens.main.mvvm.MainViewModel
import com.gigcreator.hometv.screens.navgraph.Screen
import com.gigcreator.hometv.ui.theme.HomeTVTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            HomeTVTheme {
                AppSetup()
            }
        }
    }
}

@Composable
fun AppSetup() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MainViewModel>()

    NavHost(
        navController,
        startDestination = Screen.Main
    ) {
        composable<Screen.Main> {
            //val args = it.toRoute<Screen.Main>()
            MainScreen(viewModel)
        }
    }
}
