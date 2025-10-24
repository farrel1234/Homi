package com.example.homi.model

import com.google.gson.annotations.SerializedName


data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    @SerializedName("full_name") val fullName: String,
    val phone: String,
    @SerializedName("role_id") val roleId: Int = 2
)
