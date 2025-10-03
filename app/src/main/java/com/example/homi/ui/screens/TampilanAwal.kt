package com.example.homi.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

@Composable
fun TampilanAwalScreen(
    onNextClicked: () -> Unit = {}
) {
    val laBelleAurore = FontFamily(Font(R.font.la_belle_aurore))

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Gambar background atas
        Image(
            painter = painterResource(id = R.drawable.loading_screen), // ganti dengan gambar rumah
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Bagian bawah dengan wave biru
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                val path = Path().apply {
                    moveTo(0f, height * 0.65f)
                    quadraticBezierTo(
                        width * 0.25f, height * 0.55f,
                        width * 0.5f, height * 0.65f
                    )
                    quadraticBezierTo(
                        width * 0.75f, height * 0.75f,
                        width, height * 0.65f
                    )
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                }
                drawPath(path, color = Color(0xFF2E6E93)) // biru
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Row {
                    Text(
                        text = "di ",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Homi",
                        fontSize = 26.sp,
                        fontFamily = laBelleAurore,
                        color = Color(0xFFF7C0A2) // peach
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Tombol selanjutnya
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = "Selanjutnya",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(end = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTampilanAwal() {
    MaterialTheme {
        TampilanAwalScreen()
    }
}
