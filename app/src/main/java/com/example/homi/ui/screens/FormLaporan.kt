package com.example.homi.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R
import kotlinx.coroutines.delay

/* ===== Theme tokens ===== */
private val BlueMain     = Color(0xFF2F7FA3)
private val BlueBorder   = Color(0xFF2F7FA3)
private val BlueText     = Color(0xFF2F7FA3)
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
    @DrawableRes icUpload: Int = R.drawable.kamera,
    @DrawableRes successImage: Int? = R.drawable.bahagia,
    @DrawableRes bellIcon: Int = R.drawable.notif
) {
    val poppins = FontFamily(Font(R.font.poppins_regular))

    // state input
    var nama by rememberSaveable { mutableStateOf("") }
    var tanggal by rememberSaveable { mutableStateOf("") }
    var tempat by rememberSaveable { mutableStateOf("") }
    var perihal by rememberSaveable { mutableStateOf("") }

    // state popup
    var showPopup by rememberSaveable { mutableStateOf(false) }

    // ROOT stack
    Box(modifier = Modifier.fillMaxSize()) {

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
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.width(24.dp))
            }

            /* Subjudul */
            Text(
                text = "Untuk melaporkan masalah di area lingkungan Anda,\nsilahkan mengisi data formulir dibawah ini:",
                fontFamily = PoppinsReg,
                fontSize = 12.sp,
                color = Color.White,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
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
                        Spacer(Modifier.height(16.dp))
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
                                onValueChange = { tanggal = it }
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
                                Spacer(Modifier.height(24.dp))
                            }

                            Button(
                                onClick = {
                                    // Munculkan popup dulu; submit/navigasi dipanggil saat popup selesai
                                    showPopup = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA06B)),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 35.dp)
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
                            Spacer(Modifier.height(24.dp))
                        }
                    }
                }
            }
        }

        /* ===== POPUP sesuai desain ===== */
        if (showPopup) {
            SuccessPopup10s(
                successImage = successImage,
                bellIcon = bellIcon,
                message = "Formulir Pengaduan Anda\nBerhasil Dikirim !",
                onFinished = {
                    showPopup = false
                    // Lakukan submit/navigasi setelah popup selesai
                    onKonfirmasi?.invoke(nama, tanggal, tempat, perihal)
                    // (opsional) reset field
                    nama = ""; tanggal = ""; tempat = ""; perihal = ""
                }
            )
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

/* ===== POPUP builder (overlay) ===== */
@Composable
private fun SuccessPopup10s(
    @DrawableRes successImage: Int? = null,
    @DrawableRes bellIcon: Int,
    message: String,
    onFinished: () -> Unit
) {
    // Auto close 10 detik
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(10_000L)
        onFinished()
    }

    // Scrim + container untuk badge overlap
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000)) // scrim 60%
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { /* blokir tap ke bawah */ },
        contentAlignment = Alignment.TopCenter
    ) {
        // Wrapper agar badge bisa "menempel" di atas kartu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 88.dp), // jarak dari tepi atas layar
            contentAlignment = Alignment.TopCenter
        ) {
            // === KARTU UTAMA (matching desain) ===
            val cardShape = RoundedCornerShape(22.dp)
            Card(
                shape = cardShape,
                border = BorderStroke(2.dp, BlueBorder),                   // garis tepi biru
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp), // shadow lembut
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .fillMaxWidth()
                    .padding(top = 32.dp) // ruang agar badge bisa overlap
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    successImage?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Sukses",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(150.dp) // proporsi seperti contoh
                        )
                        Spacer(Modifier.height(14.dp))
                    }
                    Text(
                        text = message,
                        fontFamily = PoppinsSemi,
                        fontSize = 16.sp,
                        color = TextPrimary,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }

            // === BADGE LONCENG NEMPEL DI ATAS KARTU ===
            // Cincin putih -> lingkaran biru -> ikon lonceng -> badge angka oranye
            Box(
                modifier = Modifier
                    .offset(y = (-18).dp) // overlap ke kartu seperti mockup
                    .size(72.dp),
                contentAlignment = Alignment.Center
            ) {
                // Cincin putih
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                // Lingkaran biru
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(BlueMain),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = bellIcon),
                        contentDescription = "Notifikasi",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(28.dp)
                    )
                    // Titik/angka notifikasi di pojok kanan atas (oranye dengan angka "1")
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 6.dp, y = (-6).dp) // posisi persis kayak contoh
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFF9966)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1",
                                color = Color.White,
                                fontFamily = PoppinsSemi,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

/* ===== Preview ===== */
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewFormPengaduan() {
    MaterialTheme {
        FormPengaduanScreen(
            onKonfirmasi = { _, _, _, _ -> /* no-op di preview */ }
        )
    }
}
