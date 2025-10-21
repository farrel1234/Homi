package com.example.homi.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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

/* ===== TOKENS ===== */
private val BlueBorder   = Color(0xFF2F7FA3)
private val TitleOrange  = Color(0xFFE69B73)
private val TextPrimary  = Color(0xFF121212)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

@Composable
fun DetailRiwayatPengaduan(
    nama: String = "Lily",
    tanggal: String = "1 Oktober 2025",
    tempat: String = "di depan lapangan voli",
    perihal: String =
        "Sampah Berserakan di Jalan, lingkungan menjadi kotor, bau dan banyak lalat.",
    @DrawableRes headerImage: Int = R.drawable.sampah
) {
    val outerRadius = 36.dp
    val cardRadius  = 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // <- pastikan putih, biar ga ada warna lain
    ) {
        /** HEADER FOTO — dilebarkan supaya nutup area atas */
        Image(
            painter = painterResource(id = headerImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),          // <- dinaikkan (nutup area hijau/warna lain)
            contentScale = ContentScale.Crop
        )

        /** LEMBAR PUTIH MELENGKUNG – ditarik ke atas supaya nempel foto */
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(topStart = outerRadius, topEnd = outerRadius),
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-48).dp)     // <- ditarik lebih ke atas
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Riwayat Pengaduan",
                    fontFamily = PoppinsSemi,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = TitleOrange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    textAlign = TextAlign.Center
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(cardRadius),
                    border = androidx.compose.foundation.BorderStroke(2.dp, BlueBorder),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        ValueLine(nama)
                        DividerLine()

                        ValueLine(tanggal)
                        DividerLine()

                        ValueLine(tempat)
                        DividerLine()

                        ValueParagraph(perihal)
                        DividerLine()
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

/* ===== SUB UI ===== */

@Composable
private fun ValueLine(text: String) {
    Text(
        text = text,
        fontFamily = PoppinsReg,
        fontSize = 14.sp,
        color = TextPrimary
    )
}

@Composable
private fun ValueParagraph(text: String) {
    Text(
        text = text,
        fontFamily = PoppinsReg,
        fontSize = 14.sp,
        color = TextPrimary,
        lineHeight = 20.sp
    )
}

@Composable
private fun DividerLine() {
    Spacer(Modifier.height(8.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .clip(RoundedCornerShape(1.dp))
            .background(BlueBorder.copy(alpha = 0.75f))
    )
    Spacer(Modifier.height(12.dp))
}

/* ===== PREVIEW ===== */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewDetailRiwayatPengaduan() {
    MaterialTheme { DetailRiwayatPengaduan() }
}