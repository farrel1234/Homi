package com.example.homi.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import com.example.homi.model.RegisterRequest
import com.example.homi.model.LoginRequest
import com.example.homi.model.AuthResponse



// ==== MODEL RESPONSE ====

data class UserData(
    val id: Int,
    @SerializedName("role_id") val roleId: Int,
    val username: String,
    val email: String,
    @SerializedName("full_name") val fullName: String,
    val phone: String,
    @SerializedName("is_active") val isActive: Int
)

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: UserData
)

data class LoginRequest(
    val identifier: String,
    val password: String
)


// ==== API INTERFACE ====

interface HomiApi {



    // Tambahkan header agar request dikirim sebagai JSON
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("login.php")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>


    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("register.php")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("refresh.php")
    suspend fun refreshToken(@retrofit2.http.Header("Authorization") refreshAuthHeader: String)
            : retrofit2.Response<AuthResponse>

    @POST("logout.php")
    suspend fun logout(): Response<AuthResponse>

    // contoh endpoint GET
    //@GET("get_pengumuman.php")
    //suspend fun getPengumuman(): Response<List<PengumumanResponse>>
}
