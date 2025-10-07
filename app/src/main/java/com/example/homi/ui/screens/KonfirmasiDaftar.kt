package com.example.homi.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
fun KonfirmasiDaftarScreen() {
    val poppins = FontFamily(Font(R.font.poppins_semibold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

    var otpCode by remember { mutableStateOf("") }

    val infiniteTransition = rememberInfiniteTransition()
    val scaleAnim by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // 🔹 Background utama
        Image(
            painter = painterResource(id = R.drawable.konfirmasi_pendaftaran),
            contentDescription = "Background Konfirmasi",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Judul
            Text(
                text = "Konfirmasi Pendaftaran",
                fontFamily = poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Gambar amplop di tengah
            Image(
                painter = painterResource(id = R.drawable.amplop),
                contentDescription = "Amplop Icon",
                modifier = Modifier.size(210.dp)
            )

            Spacer(modifier = Modifier.height(70.dp)) // posisi pas biar card nempel ke panah

            // 🔹 Card OTP dengan border biru
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF2E6F8E), RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Kode OTP",
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = otpCode,
                        onValueChange = { otpCode = it },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2E6F8E),
                            unfocusedBorderColor = Color(0xFF2E6F8E)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "*Masukkan kode yang sudah dikirimkan ke alamat Email Anda",
                        fontFamily = poppinsRegular,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Kirim Ulang Kode : 00.30",
                        fontFamily = poppins,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // 🔹 Tombol panah (berdenyut) nempel ke card
            Box(
                modifier = Modifier
                    .offset(y = (-30).dp) // posisi pas nempel ke card
                    .scale(scaleAnim)
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF4A77E)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.panah),
                    contentDescription = "Arrow Icon",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewKonfirmasiDaftar() {
    MaterialTheme {
        KonfirmasiDaftarScreen()
    }
}
