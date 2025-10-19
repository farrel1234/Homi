package com.example.homi.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

/* ===== THEME COLORS ===== */
private val BlueMain = Color(0xFF2F7FA3)
private val BlueBorder = Color(0xFF2F7FA3)
private val BlueText = Color(0xFF2F7FA3)
private val AccentOrange = Color(0xFFFF9966)
private val TextPrimary = Color(0xFF0E0E0E)
private val DividerGray = Color(0xFF2F7FA3)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg = FontFamily(Font(R.font.poppins_regular))

@Composable
fun DetailRiwayatPengaduanScreen(
    namaPelapor: String = "Lily",
    tanggal: String = "1 Oktober 2025",
    tempat: String = "di depan lapangan voli",
    perihal: String = "Sampah Berserakan di Jalan, lingkungan menjadi kotor, bau dan banyak lalat.",
    @DrawableRes headerImage: Int = R.drawable.sampah, // background foto
    @DrawableRes backIcon: Int = R.drawable.panahkembali, // ikon panah
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        /* ===== Header Gambar ===== */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
        ) {
            Image(
                painter = painterResource(headerImage),
                contentDescription = "Foto Pengaduan",
                modifier = Modifier.fillMaxSize(),

            )
            IconButton(
                onClick = { onBack?.invoke() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .size(36.dp)
                    .clip(CircleShape)
            ) {
                Icon(painter = painterResource(backIcon), contentDescription = "Kembali")
            }
        }

        /* ===== Konten Putih ===== */
        Spacer(Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Judul
                Text(
                    text = "Riwayat Pengaduan",
                    fontFamily = PoppinsSemi,
                    fontSize = 20.sp,
                    color = AccentOrange,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                // Kartu detail
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, BlueBorder),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                    ) {
                        DetailField("Nama Pelapor", namaPelapor)
                        DividerLine()

                        DetailField("Tanggal", tanggal)
                        DividerLine()

                        DetailField("Tempat", tempat)
                        DividerLine()

                        DetailField("Perihal", perihal, true)
                    }
                }
            }
        }
    }
}

/* ===== Reusable Subcomponents ===== */
@Composable
private fun DetailField(title: String, value: String, multiLine: Boolean = false) {
    Text(
        text = title,
        fontFamily = PoppinsSemi,
        fontSize = 14.sp,
        color = BlueText
    )
    Spacer(Modifier.height(4.dp))
    Text(
        text = value,
        fontFamily = PoppinsReg,
        fontSize = 14.sp,
        color = TextPrimary,
        lineHeight = if (multiLine) 20.sp else 18.sp
    )
}

@Composable
private fun DividerLine() {
    Spacer(Modifier.height(6.dp))
    Divider(
        color = DividerGray,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(10.dp))
}

/* ===== Preview ===== */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewDetailRiwayatPengaduan() {
    MaterialTheme {
        DetailRiwayatPengaduanScreen()
    }
}
