package com.example.homi.model

import com.google.gson.annotations.SerializedName


data class AuthResponse(
    @SerializedName("user") val user: User?,
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("token_type") val tokenType: String?,
    @SerializedName("expires_in") val expiresIn: Long?
)


