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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

/* ===== Theme tokens ===== */
private val BlueMain     = Color(0xFF2F7FA3)
private val BlueBorder   = Color(0xFF2F7FA3)
private val BlueText     = Color(0xFF2F7FA3)
private val AccentOrange = Color(0xFFFF9966)
private val TextPrimary  = Color(0xFF0E0E0E)
private val LineDark     = Color(0xFF1F1F1F) // garis bawah hitam tipis seperti desain

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

@Composable
fun DetailRiwayatPengaduanScreen(
    // Data
    namaPelapor: String = "Lily",
    tanggal: String = "1 Oktober 2025",
    tempat: String = "di depan lapangan voli",
    perihal: String =
        "Sampah Berserakan di Jalan, lingkungan menjadi kotor, bau dan banyak lalat.",
    // Resources
    @DrawableRes headerImage: Int = R.drawable.sampah, // ganti fotomu
    @DrawableRes backIcon: Int = R.drawable.panahkembali,             // ganti panahmu
    // Action
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        /* ===== HEADER IMAGE (rounded bottom) ===== */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
        ) {
            Image(
                painter = painterResource(headerImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { onBack?.invoke() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .size(36.dp)
                    .clip(CircleShape)

            ) {
                Icon(painterResource(backIcon), contentDescription = "Kembali")
            }
        }

        /* ===== WHITE CONTAINER (rounded big top) ===== */
        Spacer(Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // biar memanjang ke bawah seperti mockup
            shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 16.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Judul oranye
                Text(
                    text = "Riwayat Pengaduan",
                    fontFamily = PoppinsSemi,
                    fontSize = 22.sp,
                    color = AccentOrange,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                // Kartu detail (border biru, rounded)
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

                        FieldTitle("Nama Pelapor")
                        FieldValue(namaPelapor)
                        FieldUnderline()

                        FieldTitle("Tanggal")
                        FieldValue(tanggal)
                        FieldUnderline()

                        FieldTitle("Tempat")
                        FieldValue(tempat)
                        FieldUnderline()

                        FieldTitle("Perihal")
                        FieldValue(perihal, multiline = true)
                    }
                }
            }
        }
    }
}

/* ===== Sub components ===== */
@Composable
private fun FieldTitle(text: String) {
    Text(
        text = text,
        fontFamily = PoppinsSemi,
        fontSize = 14.sp,
        color = BlueText
    )
    Spacer(Modifier.height(6.dp))
}

@Composable
private fun FieldValue(text: String, multiline: Boolean = false) {
    Text(
        text = text,
        fontFamily = PoppinsReg,
        fontSize = 14.sp,
        color = TextPrimary,
        lineHeight = if (multiline) 20.sp else 18.sp
    )
}

@Composable
private fun FieldUnderline() {
    Spacer(Modifier.height(6.dp))
    Divider(
        color = LineDark,
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
