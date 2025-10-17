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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.homi.R
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.text.font.FontWeight

/* ===== Theme tokens ===== */
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
    @DrawableRes backIcon: Int = R.drawable.panahkembali,
    @DrawableRes icUpload: Int = R.drawable.kamera
) {
    val poppins = FontFamily(Font(R.font.poppins_regular))
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
                painter = painterResource(id = backIcon),
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
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(24.dp)) // spacer dummy agar judul tetap center
        }

        /* Subjudul */
        Text(
            text = "Untuk melaporkan masalah di area lingkungan Anda,\nsilahkan mengisi data formulir dibawah ini:",
            fontFamily = PoppinsReg,
            fontSize = 12.sp,
            color = Color.White,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )

        Spacer(Modifier.height(16.dp))

        /* Kontainer putih rounded */
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

                // Card form dengan border biru
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
                            onValueChange = { tanggal = it } // bisa dihubungkan ke date picker
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
                        Column(horizontalAlignment = Alignment.Start) {
                            Box(
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(UploadBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = icUpload),
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

                // Tombol Konfirmasi
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA06B)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Konfirmasi",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

/* ===== Subcomponents ===== */

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

/* ===== Preview ===== */
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewFormPengaduan() {
    MaterialTheme {
        FormPengaduanScreen()
    }
}
