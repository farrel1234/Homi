package com.example.homi.repository

import com.example.homi.network.HomiApi
import com.example.homi.model.LoginRequest
import com.example.homi.model.AuthResponse
import com.example.homi.model.RegisterRequest
import com.example.homi.util.TokenManager
import retrofit2.Response

class AuthRepository(
    private val api: HomiApi,
    private val tokenManager: TokenManager
) {

    suspend fun login(req: LoginRequest): Response<AuthResponse> {
        val res = api.login(req)
        if (res.isSuccessful) {
            res.body()?.let { body ->
                body.accessToken?.let { token -> tokenManager.saveAccessToken(token) }
                body.refreshToken?.let { rt -> tokenManager.saveRefreshToken(rt) }
            }
        }
        return res
    }

    // Sesuaikan tipe request ini dengan HomiApi.register()
    suspend fun register(req: RegisterRequest)
        : Response<AuthResponse> {
        val res = api.register(req)
        if (res.isSuccessful) {
            res.body()?.let { body ->
                body.accessToken?.let { tokenManager.saveAccessToken(it) }
                body.refreshToken?.let { tokenManager.saveRefreshToken(it) }
            }
        }
        return res
    }

    suspend fun logout(): Response<AuthResponse> {
        val res = api.logout()
        // Amanin sisi klien
        tokenManager.clear()
        return res
    }
}
