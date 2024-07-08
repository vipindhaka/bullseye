package com.example.kodeco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kodeco.screens.AboutScreen
import com.example.kodeco.screens.GameScreen
import com.example.kodeco.ui.theme.KodecoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            KodecoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //GameScreen()
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "gamescreen"){
        composable("gamescreen"){ GameScreen(onNavigateToAbout = {
            navController.navigate(route = "about")
        })}
        composable("about"){ AboutScreen(navigateBack = {
            navController.navigateUp()
        })}
    }
}

