package com.example.homi.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int? = null,

    @SerializedName("role_id")
    val roleId: Int? = null,

    val username: String? = null,
    val email: String? = null,

    @SerializedName("full_name")
    val fullName: String? = null,

    val phone: String? = null,

    @SerializedName("is_active")
    val isActive: Int? = null
)
