package com.example.homi.navigation

sealed class Routes(val route: String) {
    object Home : Routes("nav_home")
    object Direktori : Routes("nav_direktori")
    object Riwayat : Routes("nav_riwayat")
    data object Dashboard  : Routes("dashboard")
    data object FormAjuan1 : Routes("form_ajuan_1")
    object Splash : Routes("splash")
    object TampilanAwal1 : Routes("tampilan_awal_1")
    object TampilanAwal2 : Routes("tampilan_awal_2")
    object TampilanAwal3 : Routes("tampilan_awal_3")
    object Otp1 : Routes("otp_1")
    object Otp2 : Routes("otp_2")
    object Otp3 : Routes("otp_3")
    object Login : Routes("login")
    object Register : Routes("register")

}
