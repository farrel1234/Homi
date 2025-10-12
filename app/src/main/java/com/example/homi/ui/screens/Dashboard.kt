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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

@Composable
fun DashboardScreen() {
    val poppins = FontFamily(Font(R.font.poppins_semibold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

    Box(modifier = Modifier.fillMaxSize()) {
        // 🔹 Background
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
            // HEADER
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
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Selamat Datang di Homi",
                        fontFamily = poppins,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Menghubungkan Warga, Membangun Kebersamaan",
                        fontFamily = poppinsRegular,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(44.dp))

            // 🔸 Pengumuman
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pengumuman",
                    fontFamily = poppins,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE26A2C),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(13.dp))

            // 🔸 Box Pengumuman
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_pengumuman),
                    contentDescription = "Kegiatan Gotong Royong",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, end = 16.dp, bottom = 28.dp)
                ) {
                    val suezOne = FontFamily(Font(R.font.suez_one_regular))
                    Text(
                        text = "Kegiatan Gotong Royong",
                        fontFamily = suezOne,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        text = "Jumat/3 Sep 25,\nArea Masjid,\nSemua Warga\nPerumahan Hawai\nGarden",
                        fontFamily = poppinsRegular,
                        fontSize = 13.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🔸 Menu Utama
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Box hijau cuma untuk pengajuan
                DashboardMenuItem(icon = R.drawable.icon_pengajuan, text = "")

                Spacer(modifier = Modifier.height(20.dp))

                // Icon lainnya di luar box hijau
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DashboardPlainIcon(icon = R.drawable.icon_pengaduan)
                    DashboardPlainIcon(icon = R.drawable.icon_pembayaran)
                    DashboardPlainIcon(icon = R.drawable.icon_keuangan)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // 🔹 Navbar dengan Card hijau di bawah layar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp),
            contentAlignment = Alignment.Center
        ) {
            // 🔸 Card hijau sebagai background navbar
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF31708E)), // warna hijau tua
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {}

            // 🔸 Isi navbar (ikon dan label)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center),
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
fun DashboardPlainIcon(icon: Int) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
    )
}

@Composable
fun NavBarItem(icon: Int, label: String, isActive: Boolean = false) {
    val poppins = FontFamily(Font(R.font.poppins_semibold))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
