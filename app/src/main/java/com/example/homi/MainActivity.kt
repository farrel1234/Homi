package com.example.homi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.homi.ui.navigation.NavGraph
import com.example.homi.ui.theme.HomiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HomiTheme {
                MaterialTheme {
                    NavGraph() // 🔹 Mulai dari Dashboard → FormAjuan1
                }
            }
        }
    }
}
