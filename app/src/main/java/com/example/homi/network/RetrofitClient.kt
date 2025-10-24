package com.example.homi.network

import android.content.Context
import com.example.homi.util.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private const val BASE_URL = "http://10.170.19.176:8080/homi-api/api/"

    val instance: HomiApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(HomiApi::class.java)
    }

    fun create(context: Context): HomiApi {
        val tokenManager = TokenManager(context)

        val logging = HttpLoggingInterceptor().apply {
            // kalau mau, turunkan ke NONE di release
            level = HttpLoggingInterceptor.Level.BODY
        }

        // 1) Retrofit tanpa client dulu, agar bisa dipakai di Authenticator (hindari rekursi)
        val retrofitCore = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 2) Client dengan interceptor + authenticator ( authenticator memakai retrofitCore )
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(logging)
            .authenticator(TokenAuthenticator(retrofitCore, tokenManager))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        // 3) Retrofit final dengan client di atas
        val retrofit = retrofitCore.newBuilder()
            .client(client)
            .build()

        return retrofit.create(HomiApi::class.java)
    }
}
