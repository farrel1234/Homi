package com.example.homi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homi.navigation.Routes
import com.example.homi.ui.screens.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard.route
    ) {
        // ===== Dashboard =====
        composable(Routes.Dashboard.route) {
            DashboardScreen(
                onPengajuan = { navController.navigate(Routes.FormAjuan1.route) },
                onPengaduan = { /* TODO: navController.navigate("pengaduan") */ },
                onPembayaran = { /* TODO: navController.navigate("pembayaran") */ }
            )
        }

        // ===== Form Ajuan 1 =====
        composable(Routes.FormAjuan1.route) {
            FormAjuan1(
                onBack = { navController.popBackStack() },
                onKonfirmasi = {
                    // contoh: nanti bisa diarahkan ke form selanjutnya
                    navController.popBackStack()
                }
            )
        }
    }
}

/* Helper opsional untuk menghindari duplikasi navigasi */
private fun NavController.safeNavigateSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.startDestinationId) { saveState = true }
    }
}
