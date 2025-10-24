package com.example.homi.ui

//import android.content.Context
//import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homi.model.LoginRequest
import com.example.homi.model.RegisterRequest
import com.example.homi.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



data class UiState(
    val loading: Boolean = false,
    val message: String? = null,
    val loggedIn: Boolean = false
)

//register
data class RegisterUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val message: String? = ""
)

class AuthViewModel(private val repo: AuthRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    // REGISTER
    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState = _registerState.asStateFlow()

    fun clearRegisterState() {
        _registerState.value = RegisterUiState()  // kembali ke Idle
    }

    // Opsional kalau kamu mau pisah reset pesan saja
    fun clearRegisterMessage() {
        _registerState.value = _registerState.value.copy(message = null)
    }

    fun register(req: RegisterRequest) {
        viewModelScope.launch {
            _registerState.value = RegisterUiState(isLoading = true)
            try {
                val res = repo.register(req)
                val body = res.body()
                val err  = res.errorBody()?.string().orEmpty()

                val ok = res.isSuccessful && (body?.user != null || !body?.accessToken.isNullOrBlank())

                _registerState.value = RegisterUiState(
                    isLoading = false,
                    success = ok,
                    message = when {
                        ok -> "Registrasi berhasil"
                        err.isNotBlank() -> err
                        else -> "Registrasi gagal (${res.code()})"
                    }
                )

            } catch (e: Exception) {
                _registerState.value = RegisterUiState(
                    isLoading = false,
                    success = false,
                    message = "Error: ${e.message}"
                )
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            try {
                val res = repo.login(LoginRequest(username, password = password))
                val ok  = res.isSuccessful && (res.body()?.accessToken != null)
                val err = res.errorBody()?.string().orEmpty()
                _state.value = if (ok) {
                    UiState(message = "Login berhasil", loggedIn = true)
                } else {
                    UiState(message = if (err.isNotBlank()) "Login gagal: $err" else "Login gagal")
                }
            } catch (e: Exception) {
                _state.value = UiState(loading = false, message = "Login error: ${e.message}")
            }
        }
    }


    fun logout() {
        viewModelScope.launch {
            repo.logout()
            _state.value = UiState(message = "Logout berhasil", loggedIn = false)
        }
    }
}


