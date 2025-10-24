package com.example.homi.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homi.network.RetrofitClient
import com.example.homi.repository.AuthRepository
import com.example.homi.util.TokenManager

class AuthViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api = RetrofitClient.create(context)
        val tokenManager = TokenManager(context)
        val repo = AuthRepository(api, tokenManager) // sesuaikan dgn signature repositorimu
        return AuthViewModel(repo) as T
    }
}
