package com.example.homi.navigation

sealed class Routes(val route: String) {
    object Home : Routes("nav_home")
    object Direktori : Routes("nav_direktori")
    object Riwayat : Routes("nav_riwayat")
}
