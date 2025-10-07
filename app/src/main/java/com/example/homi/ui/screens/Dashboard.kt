package com.example.homi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

@Composable
fun DashboardScreen() {
    val poppins = FontFamily(Font(R.font.poppins_semibold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

    Box(modifier = Modifier.fillMaxSize()) {
        // 🔹 Background utama dashboard
        Image(
            painter = painterResource(id = R.drawable.bg_dashboard),
            contentDescription = "Background Dashboard",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // 🔹 Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_profile),
                    contentDescription = "Profil",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hai, Lily",
                        fontFamily = poppins,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Selamat Datang di Homi",
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Menghubungkan Warga, Membangun Kebersamaan",
                        fontFamily = poppinsRegular,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.notif),
                    contentDescription = "Notifikasi",
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Label “Pengumuman”
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pengumuman",
                    fontFamily = poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE26A2C)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Gambar pengumuman
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_pengumuman),
                    contentDescription = "Kegiatan Gotong Royong",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Kegiatan Gotong Royong",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Jumat/3 Sep 25,\nArea Masjid,\nSemua Warga\nPerumahan Hawai'i Garden",
                        fontFamily = poppinsRegular,
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // 🔹 Menu utama (4 kotak fitur)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DashboardMenuItem(
                        icon = R.drawable.icon_pengajuan,
                        text = "Pengajuan\nLayanan"
                    )
                    DashboardMenuItem(
                        icon = R.drawable.icon_pengaduan,
                        text = "Pengaduan\nWarga"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DashboardMenuItem(
                        icon = R.drawable.icon_pembayaran,
                        text = "Pembayaran\nIuran"
                    )
                    DashboardMenuItem(
                        icon = R.drawable.icon_keuangan,
                        text = "Laporan\nKeuangan"
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Navigation bar bawah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavBarItem(R.drawable.homeoren, "Beranda", isActive = true)
                    NavBarItem(R.drawable.icon_direktori, "Direktori")
                    NavBarItem(R.drawable.icon_riwayat, "Riwayat")
                    NavBarItem(R.drawable.icon_akun, "Akun")
                }
            }
        }
    }
}

@Composable
fun DashboardMenuItem(icon: Int, text: String) {
    val poppins = FontFamily(Font(R.font.poppins_semibold))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(130.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF31708E))
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            fontFamily = poppins,
            fontSize = 12.sp,
            color = Color(0xFFFFD3A4),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NavBarItem(icon: Int, label: String, isActive: Boolean = false) {
    val poppins = FontFamily(Font(R.font.poppins_semibold))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = label,
            fontFamily = poppins,
            fontSize = 10.sp,
            color = if (isActive) Color(0xFFE26A2C) else Color.Gray
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDashboard() {
    MaterialTheme {
        DashboardScreen()
    }
}
