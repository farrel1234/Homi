package com.example.homi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

/* ========= Tokens ========= */
private val BlueMain     = Color(0xFF2F7FA3)
private val BlueBorder   = Color(0xFF2F7FA3)
private val BlueText     = Color(0xFF2F7FA3)
private val LineGray     = Color(0xFF858282)
private val TextPrimary  = Color(0xFF0E0E0E)
private val TextMuted    = Color(0xFF8A8A8A)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

/* ========= Screen ========= */
@Composable
fun Riwayat1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueMain)
            .statusBarsPadding()
    ) {
        /* Header */
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // kosongkan slot kiri supaya judul tetap center
            Spacer(Modifier.width(24.dp))
            Text(
                text = "Riwayat Pengaduan",
                fontFamily = PoppinsSemi,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(24.dp))
        }

        Spacer(Modifier.height(6.dp))
        Text(
            text = "Anda dapat melihat riwayat pengajuan dan pengaduan\n" +
                    "yang telah di ajukan oleh Anda",
            fontFamily = PoppinsReg,
            fontSize = 12.sp,
            color = Color.White,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        /* Container putih */
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                /* Segmented tabs */
                var selected by remember { mutableStateOf(1) } // 0=Pengajuan, 1=Pengaduan (default sesuai screenshot)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SegTab(
                        text = "Pengajuan",
                        selected = selected == 0,
                        onClick = { selected = 0 }
                    )
                    SegTab(
                        text = "Pengaduan",
                        selected = selected == 1,
                        onClick = { selected = 1 }
                    )
                }

                Spacer(Modifier.height(16.dp))

                /* List kartu riwayat */
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sampleRiwayat) { item ->
                        RiwayatCard(item)
                    }
                }
            }
        }
    }
}

/* ========= Components ========= */

@Composable
private fun SegTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    if (selected) {
        // Filled (aktif) – biru
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BlueMain),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(text, fontFamily = PoppinsSemi, fontSize = 16.sp, color = Color.White)
        }
    } else {
        // Outlined (tidak aktif) – putih dengan border biru
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(28.dp),
            border = BorderStroke(2.dp, BlueBorder),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = BlueMain)
        ) {
            Text(text, fontFamily = PoppinsSemi, fontSize = 16.sp, color = BlueMain)
        }
    }
}

data class RiwayatItem(
    val nama: String,
    val tanggal: String,
    val tempat: String,
    val perihal: String
)

@Composable
private fun RiwayatCard(item: RiwayatItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(1.dp),
        border = BorderStroke(1.dp, LineGray),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {

            RowLine("Nama Pelapor", item.nama)
            Spacer(Modifier.height(8.dp))
            RowLine("Tanggal", item.tanggal)
            Spacer(Modifier.height(8.dp))
            RowLine("Tempat", item.tempat)



            Spacer(Modifier.height(10.dp))
            Text(
                text = "Perihal : ${item.perihal}",
                fontFamily = PoppinsSemi,
                fontSize = 13.sp,
                color = BlueText
            )
        }
    }
}

@Composable
private fun RowLine(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$label  : ",
            fontFamily = PoppinsReg,
            fontSize = 13.sp,
            color = TextMuted
        )
        Text(
            text = value,
            fontFamily = PoppinsReg,
            fontSize = 13.sp,
            color = TextPrimary
        )
    }
}

/* ========= Sample data ========= */
private val sampleRiwayat = listOf(
    RiwayatItem("Lily", "1 Oktober 2025", "di depan lapangan voli", "Sampah Berserakan di Jalan"),
    RiwayatItem("Lily", "1 Oktober 2025", "di depan lapangan voli", "Sampah Berserakan di Jalan"),
    RiwayatItem("Lily", "1 Oktober 2025", "di depan lapangan voli", "Sampah Berserakan di Jalan"),
    RiwayatItem("Lily", "1 Oktober 2025", "di depan lapangan voli", "Sampah Berserakan di Jalan"),
)

/* ========= Preview ========= */
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewRiwayat1() {
    MaterialTheme { Riwayat1Screen() }
}
