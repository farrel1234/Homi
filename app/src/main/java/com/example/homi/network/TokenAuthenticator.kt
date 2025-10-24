package com.example.homi.network

import com.example.homi.util.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import com.example.homi.model.AuthResponse

class TokenAuthenticator(
    private val retrofit: Retrofit,
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Hindari loop refresh berulang
        if (responseCount(response) >= 2) return null

        val refresh = tokenManager.getRefreshToken() ?: return null
        val api = retrofit.create(HomiApi::class.java)

        // === Panggil endpoint refresh ===
        val newTokens: AuthResponse? = try {
            val res = runBlocking {
                // OPSI A: refresh via Authorization header "Bearer <refreshToken>"
                api.refreshToken("Bearer $refresh")
            }
            if (res.isSuccessful) res.body() else null
        } catch (_: Exception) {
            null
        }

        // Jika gagal dapat token baru -> batal
        val newAccess = newTokens?.accessToken ?: return null

        // Simpan token baru
        tokenManager.saveAccessToken(newAccess)
        newTokens.refreshToken?.let { tokenManager.saveRefreshToken(it) }

        // Ulangi request sebelumnya dengan Authorization baru
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccess")
            .build()
    }

    private fun responseCount(response: Response): Int {
        var r: Response? = response
        var count = 1
        while (r?.priorResponse != null) {
            r = r.priorResponse
            count++
        }
        return count
    }
}
