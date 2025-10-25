package com.example.homi.model

import com.google.gson.annotations.SerializedName


data class UserData(
    val id: Int,
    val role_id: Int,
    val username: String,
    val email: String,
    val full_name: String,
    val phone: String,
    val is_active: Int
)

data class AuthResponse(
    val accessToken: String,     // non-null
    val refreshToken: String,    // non-null
    val user: UserData?          // boleh null kalau login/refresh tidak kirim user
)
