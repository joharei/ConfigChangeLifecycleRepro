package com.example.configchangelifecyclerepro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.currentStateAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.configchangelifecyclerepro.ui.theme.ConfigChangeLifecycleReproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfigChangeLifecycleReproTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }

}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            LifecycleState("Outside NavHost")

            AppNavGraph()
        }
    }
}

@Composable
fun AppNavGraph() {
    NavHost(navController = rememberNavController(), startDestination = "start") {
        composable("start") {
            LifecycleState("Inside NavHost")
        }
    }
}

@Composable
fun LifecycleState(label: String) {
    val state by LocalLifecycleOwner.current.lifecycle.currentStateAsState()
    Text(text = "$label: ${state.name}")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConfigChangeLifecycleReproTheme {
        MainScreen()
    }
}
