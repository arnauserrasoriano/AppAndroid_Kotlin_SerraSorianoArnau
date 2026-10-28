package com.example.app_arnauserra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app_arnauserra.ui.SplitMiniScreen
import com.example.app_arnauserra.ui.theme.App_arnauserraTheme

// Main activity of the application.
// Configures edge-to-edge display and loads the Jetpack Compose user interface.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_arnauserraTheme {
                SplitMiniScreen()
            }
        }
    }
}
