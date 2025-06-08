package com.stephenelf.grindgym.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.stephenelf.grindgym.presentation.main.MainScreen
import com.stephenelf.grindgym.presentation.theme.GymderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymderTheme {
                MainScreen()
            }
        }
    }
}