package com.example.homi.network

import com.example.homi.util.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()

        tokenManager.getAccessToken()?.let { token ->
            if (token.isNotBlank()) {
                builder.addHeader("Authorization", "Bearer $token")
            }
        }

        // Header umum untuk PHP
        builder.addHeader("Accept", "application/json")
        builder.addHeader("Content-Type", "application/json")

        return chain.proceed(builder.build())
    }
}
