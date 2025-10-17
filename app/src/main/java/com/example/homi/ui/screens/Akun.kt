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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

/* ===== COLORS ===== */
private val BlueMain = Color(0xFF2F7FA3)
private val AccentOrange = Color(0xFFFF9966)
private val DividerLine = Color(0xFFE0E0E0)
private val TextPrimary = Color(0xFF0E0E0E)

/* ===== FONTS ===== */
private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg = FontFamily(Font(R.font.poppins_regular))

@Composable
fun AkunScreen(
    onUbahKataSandi: (() -> Unit)? = null,
    onProsesPengajuan: (() -> Unit)? = null,
    onLaporkanMasalah: (() -> Unit)? = null,
    onKeluar: (() -> Unit)? = null,
    onBeranda: (() -> Unit)? = null,
    onDirektori: (() -> Unit)? = null,
    onRiwayat: (() -> Unit)? = null,
    onAkun: (() -> Unit)? = null
) {
    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BlueMain)
                .statusBarsPadding()
        ) {
            Spacer(Modifier.height(30.dp))

            /* ===== HEADER ===== */
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_profile),
                    contentDescription = "Foto Profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(84.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Lily",
                        fontFamily = PoppinsSemi,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.width(4.dp))
                    Image(
                        painter = painterResource(R.drawable.panah),
                        contentDescription = "Edit Profil",
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            /* ===== CONTAINER PUTIH ===== */
            Spacer(Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    MenuRow("Ubah Kata Sandi", onClick = onUbahKataSandi)
                    Divider(color = DividerLine, thickness = 1.dp)

                    MenuRow("Proses Pengajuan Layanan", onClick = onProsesPengajuan)
                    Divider(color = DividerLine, thickness = 1.dp)

                    MenuRow("Laporkan Masalah", onClick = onLaporkanMasalah)
                    Divider(color = DividerLine, thickness = 1.dp)

                    MenuRow("Keluar", onClick = onKeluar)
                }
            }
        }

        /* ===== BOTTOM NAVBAR ===== */
        BottomNavBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onBeranda = onBeranda,
            onDirektori = onDirektori,
            onRiwayat = onRiwayat,
            onAkun = onAkun
        )
    }
}

/* ===== COMPONENTS ===== */
@Composable
private fun MenuRow(title: String, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = PoppinsReg,
            fontSize = 14.sp,
            color = TextPrimary
        )
        Image(
            painter = painterResource(R.drawable.panah),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

/* ===== NAVBAR ===== */
@Composable
private fun BottomNavBar(
    modifier: Modifier = Modifier,
    onBeranda: (() -> Unit)?,
    onDirektori: (() -> Unit)?,
    onRiwayat: (() -> Unit)?,
    onAkun: (() -> Unit)?,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(78.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp),
            colors = CardDefaults.cardColors(containerColor = BlueMain),
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
            NavItem(icon = R.drawable.homeoren, label = "Beranda", active = false, onClick = onBeranda)
            NavItem(icon = R.drawable.icon_direktori, label = "Direktori", active = false, onClick = onDirektori)
            NavItem(icon = R.drawable.icon_riwayat, label = "Riwayat", active = false, onClick = onRiwayat)
            NavItem(icon = R.drawable.icon_akun, label = "Akun", active = true, onClick = onAkun)
        }
    }
}

@Composable
private fun NavItem(icon: Int, label: String, active: Boolean, onClick: (() -> Unit)?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(enabled = onClick != null) { onClick?.invoke() }
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(26.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            fontFamily = PoppinsSemi,
            fontSize = 10.sp,
            color = if (active) AccentOrange else Color(0xFFECECEC)
        )
    }
}

/* ===== PREVIEW ===== */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewAkun() {
    MaterialTheme { AkunScreen() }
}
