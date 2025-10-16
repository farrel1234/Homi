package com.example.homi.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R


/* ===== Theme tokens (konsisten dengan screen sebelumnya) ===== */
private val BlueMain     = Color(0xFF2F7FA3)
private val BlueBorder   = Color(0xFF2F7FA3)
private val BlueText     = Color(0xFF2F7FA3)
private val AccentOrange = Color(0xFFFF9966)
private val TextPrimary  = Color(0xFF0E0E0E)
private val TextMuted    = Color(0xFF8A8A8A)
private val FieldLine    = Color(0xFF2F7FA3)
private val UploadBg     = Color(0xFFF0F0F0)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

@Composable
fun FormPengaduanScreen(
    onBack: (() -> Unit)? = null,
    onKonfirmasi: ((nama: String, tanggal: String, tempat: String, perihal: String) -> Unit)? = null,
    @DrawableRes backIcon: Int = R.drawable.panah,           // panah.png
    @DrawableRes icUpload: Int = R.drawable.kamera           // ganti ke ic_camera kalau ada
) {
    // state input
    var nama by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var tempat by remember { mutableStateOf("") }
    var perihal by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueMain)
    ) {
        /* Top bar */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.panahkembali),
                contentDescription = "Kembali",
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable(enabled = onBack != null) { onBack?.invoke() }
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Formulir Pengaduan",
                fontFamily = PoppinsSemi,
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(24.dp))
        }

        /* Subjudul */
        Text(
            text = "Untuk melaporkan masalah di area lingkungan Anda,\nsilahkan mengisi data formulir dibawah ini:",
            fontFamily = PoppinsReg,
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(16.dp))
        /* Kontainer putih rounded */
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))
                // Card form dengan border biru dan sudut bulat
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, BlueBorder),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Field: Nama Pelapor
                        FieldLabel("Nama Pelapor")
                        UnderlineTextField(
                            value = nama,
                            onValueChange = { nama = it }
                        )

                        Spacer(Modifier.height(16.dp))

                        // Field: Tanggal
                        FieldLabel("Tanggal")
                        UnderlineTextField(
                            value = tanggal,
                            onValueChange = { tanggal = it }, // bisa dihubungkan ke date picker eksternal
                        )

                        Spacer(Modifier.height(16.dp))

                        // Field: Tempat
                        FieldLabel("Tempat")
                        UnderlineTextField(
                            value = tempat,
                            onValueChange = { tempat = it }
                        )

                        Spacer(Modifier.height(16.dp))

                        // Field: Perihal
                        FieldLabel("Perihal")
                        UnderlineTextField(
                            value = perihal,
                            onValueChange = { perihal = it }
                        )

                        Spacer(Modifier.height(16.dp))

                        // Upload Foto
                        FieldLabel("Upload Foto")
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(UploadBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.kamera),
                                    contentDescription = "Upload",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(46.dp)
                                )
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = "Max 5 MB",
                                fontFamily = PoppinsReg,
                                fontSize = 11.sp,
                                color = TextMuted,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Tombol Konfirmasi oranye + bayangan lembut
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { onKonfirmasi?.invoke(nama, tanggal, tempat, perihal) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .shadow(8.dp, RoundedCornerShape(24.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                        shape = RoundedCornerShape(24.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        Text(
                            text = "Konfirmasi",
                            fontFamily = PoppinsSemi,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

/* ====== Subcomponents ====== */

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        fontFamily = PoppinsSemi,
        fontSize = 14.sp,
        color = BlueText
    )
    Spacer(Modifier.height(6.dp))
}

@Composable
private fun UnderlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = TextPrimary,
                fontFamily = PoppinsReg,
                fontSize = 14.sp
            ),
            cursorBrush = SolidColor(FieldLine),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )
        // garis bawah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(FieldLine.copy(alpha = 0.8f))
        )
    }
}

/* ====== Preview ====== */
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewFormPengaduan() {
    MaterialTheme {
        FormPengaduanScreen()
    }
}
