package com.example.homi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.homi.navigation.AppNavHostAnimated
import com.example.homi.network.RetrofitClient
import com.example.homi.repository.AuthRepository
import com.example.homi.ui.AuthViewModel
import com.example.homi.util.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // === 1. Siapkan dependency dasar ===
        val tokenManager = TokenManager(this)                     // untuk simpan token
        val api = RetrofitClient.create(this)                     // client Retrofit
        val repository = AuthRepository(api, tokenManager)         // komunikasi backend

        // === 2. Buat ViewModel ===
        val authViewModel = AuthViewModel(repository)              // inisialisasi VM

        // === 3. Kirim ke NavHost ===
        setContent {
            AppNavHostAnimated(
                tokenManager = tokenManager,
                authViewModel = authViewModel
            )
        }
    }
}

