package com.example.homi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

// ====== Color tokens (ambil dari screenshot) ======
private val TealMain = Color(0xFF31708E)
private val TealLight = Color(0xFF4F8EA9)
private val AccentOrange = Color(0xFFE26A2C)
private val Cream = Color(0xFFFFD3A4)
private val TextPrimary = Color(0xFF4B4B4B)

@Composable
fun DashboardScreen(
    onBeranda: (() -> Unit)? = null,
    onDirektori: (() -> Unit)? = null,
    onRiwayat: (() -> Unit)? = null,
    onAkun: (() -> Unit)? = null,
    onPengajuan: (() -> Unit)? = null,
    onPengaduan: (() -> Unit)? = null,
    onPembayaran: (() -> Unit)? = null,
    onKeuangan: (() -> Unit)? = null,
) {
    // fonts
    val poppinsSemi = FontFamily(Font(R.font.poppins_semibold))
    val poppinsReg = FontFamily(Font(R.font.poppins_regular))
    val suezOne = FontFamily(Font(R.font.suez_one_regular))

    Box(Modifier.fillMaxSize()) {
        // Background dekoratif (opsional)
        Image(
            painter = painterResource(id = R.drawable.bg_dashboard),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // ===== Header =====
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_profile),
                    contentDescription = "Profil",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        "Hai, Lily",
                        fontFamily = poppinsSemi,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Selamat Datang di Homi",
                        fontFamily = poppinsSemi,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Text(
                        "Menghubungkan Warga, Membangun Kebersamaan",
                        fontFamily = poppinsReg,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
                Spacer(Modifier.width(8.dp))
                // ikon notifikasi (opsional)
                /*Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )*/
            }

            Spacer(Modifier.height(32.dp))

            // ===== Judul "Pengumuman" =====
            Text(
                text = "Pengumuman",
                fontFamily = poppinsSemi,
                fontSize = 18.sp,
                color = AccentOrange,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(10.dp))

            // ===== Kartu Pengumuman =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_pengumuman),
                    contentDescription = "Kegiatan Gotong Royong",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // overlay
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.35f))
                )

                // judul tengah + detail kiri bawah
                Text(
                    text = "Kegiatan Gotong Royong",
                    fontFamily = suezOne,
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Jumat/3 Sep 25,\nArea Masjid,\nSemua Warga\nPerumahan Hawai\nGarden",
                    fontFamily = poppinsReg,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // ===== Grid Menu 2x2 (sesuai screenshot) =====
            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DashboardMenuItem(
                        icon = R.drawable.icon_pengajuan,
                        title = "Pengajuan\nLayanan",
                        onClick = onPengajuan
                    )
                    DashboardMenuItem(
                        icon = R.drawable.icon_pengaduan,
                        title = "Pengaduan\nWarga",
                        onClick = onPengaduan
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DashboardMenuItem(
                        icon = R.drawable.icon_pembayaran,
                        title = "Pembayaran\nIuran",
                        onClick = onPembayaran
                    )
                    DashboardMenuItem(
                        icon = R.drawable.icon_keuangan,
                        title = "Laporan\nKeuangan",
                        onClick = onKeuangan
                    )
                }
            }

            Spacer(Modifier.height(86.dp)) // beri ruang untuk navbar melengkung
        }

        // ===== Bottom Navbar dengan latar teal melengkung =====
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(78.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp),
                colors = CardDefaults.cardColors(containerColor = TealMain),
                shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {}

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavBarItem(
                    icon = R.drawable.homeoren, label = "Beranda",
                    isActive = true, onClick = onBeranda
                )
                NavBarItem(
                    icon = R.drawable.icon_direktori, label = "Direktori",
                    onClick = onDirektori
                )
                NavBarItem(
                    icon = R.drawable.icon_riwayat, label = "Riwayat",
                    onClick = onRiwayat
                )
                NavBarItem(
                    icon = R.drawable.icon_akun, label = "Akun",
                    onClick = onAkun
                )
            }
        }
    }
}

// ====== Reusable composables ======
@Composable
private fun DashboardMenuItem(
    icon: Int,
    title: String,
    onClick: (() -> Unit)? = null
) {
    val poppinsSemi = FontFamily(Font(R.font.poppins_semibold))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(150.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(TealMain)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(14.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier.size(42.dp)
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = title,
            fontFamily = poppinsSemi,
            fontSize = 12.sp,
            color = Cream,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

@Composable
private fun NavBarItem(
    icon: Int,
    label: String,
    isActive: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val poppinsSemi = FontFamily(Font(R.font.poppins_semibold))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(enabled = onClick != null) { onClick?.invoke() }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(26.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            fontFamily = poppinsSemi,
            fontSize = 10.sp,
            color = if (isActive) AccentOrange else Color(0xFFECECEC)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewDashboard() {
    MaterialTheme { DashboardScreen() }
}
