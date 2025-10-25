package com.example.homi.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

// Screens
import com.example.homi.ui.screens.SplashScreen
import com.example.homi.ui.screens.TampilanAwalScreen
import com.example.homi.ui.screens.TampilanAwalScreen2
import com.example.homi.ui.screens.TampilanAwalScreen3
import com.example.homi.ui.screens.LoginScreen
import com.example.homi.ui.screens.DaftarScreen
import com.example.homi.ui.screens.KonfirmasiDaftarScreen
import com.example.homi.ui.screens.DashboardScreen
import com.example.homi.ui.screens.DetailPengumumanScreen
import com.example.homi.ui.AuthViewModel
import com.example.homi.util.TokenManager

// ⬇️ pakai Routes dari file Routes.kt
// (file ini: app/src/main/java/com/example/homi/navigation/Routes.kt)
import com.example.homi.navigation.Routes


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHostAnimated(
    tokenManager: TokenManager,
    authViewModel: AuthViewModel
) {
    val navController = rememberAnimatedNavController()
    val start = if (!tokenManager.getAccessToken().isNullOrBlank())
        Routes.Beranda else Routes.Login

    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        // Splash
        composable(
            route = Routes.Splash,
            exitTransition = { fadeOut(tween(250)) },
            popEnterTransition = { fadeIn(tween(250)) }
        ) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Routes.TampilanAwal) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        // TampilanAwal → 2
        composable(
            route = Routes.TampilanAwal,
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(220)) }
        ) {
            TampilanAwalScreen(
                onNextClicked = {
                    navController.navigate(Routes.TampilanAwal2) {
                        launchSingleTop = true
                        popUpTo(Routes.TampilanAwal) { inclusive = true }
                    }
                }
            )
        }

        // 2 → 3
        composable(
            route = Routes.TampilanAwal2,
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(220)) }
        ) {
            TampilanAwalScreen2(
                onNextClicked = {
                    navController.navigate(Routes.TampilanAwal3) {
                        launchSingleTop = true
                        popUpTo(Routes.TampilanAwal2) { inclusive = true }
                    }
                }
            )
        }

        // 3 → Login
        composable(
            route = Routes.TampilanAwal3,
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(220)) }
        ) {
            TampilanAwalScreen3(
                onNextClicked = {
                    navController.navigate(Routes.Login) {
                        launchSingleTop = true
                        popUpTo(Routes.TampilanAwal3) { inclusive = true }
                    }
                }
            )
        }

        // Login
        composable(Routes.Login) {
            LoginScreen(
                onLoginClicked = { /* boleh kosong sekarang */ },
                onRegisterClicked = { navController.navigate(Routes.Daftar) },
                onForgotPasswordClicked = { /* ke layar lupa password jika ada */ },
                viewModel = authViewModel, // <-- KIRIM ViewModel yang dibuat di MainActivity
                navToDashboard = {
                    navController.navigate(Routes.Beranda) {
                        popUpTo(Routes.Login) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Daftar
        composable(
            route = Routes.Daftar,
            enterTransition = { fadeIn(tween(250)) },
            exitTransition = { fadeOut(tween(200)) }
        ) {
            DaftarScreen(
                onRegisterClicked = { navController.navigate(Routes.Konfirmasi) },
                onLoginClicked = {
                    navController.navigate(Routes.Login) {
                        launchSingleTop = true
                        popUpTo(Routes.Login) { inclusive = false }
                    }
                }
            )
        }

        // Konfirmasi OTP
        composable(
            route = Routes.Konfirmasi,
            enterTransition = { fadeIn(tween(250)) },
            exitTransition = { fadeOut(tween(200)) }
        ) {
            KonfirmasiDaftarScreen(navController = navController)
        }

        // Beranda / Dashboard
        composable(
            route = Routes.Beranda,
            enterTransition = { fadeIn(tween(220)) },
            exitTransition = { fadeOut(tween(180)) }
        ) {
            DashboardScreen(
                onPengajuan = { /* ... */ },
                onPengaduan = { /* ... */ },
                onPembayaran = { /* ... */ },
                onDetailPengumumanClicked = {
                    navController.navigate(Routes.DetailPengumuman)
                }
            )
        }

        // Detail Pengumuman (slide + fade)
        composable(
            route = Routes.DetailPengumuman,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(320),
                    initialOffsetY = { fullHeight -> fullHeight / 3 }
                ) + fadeIn(animationSpec = tween(320))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(200))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(220))
            },
            popExitTransition = {
                slideOutVertically(
                    animationSpec = tween(260),
                    targetOffsetY = { fullHeight -> fullHeight / 3 }
                ) + fadeOut(animationSpec = tween(260))
            }
        ) {
            DetailPengumumanScreen()
        }
    }
}