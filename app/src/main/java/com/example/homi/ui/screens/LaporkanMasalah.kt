package com.example.homi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

/* ===== Tokens ===== */
private val BlueMain     = Color(0xFF2F7FA3)
private val AccentOrange = Color(0xFFFF9966)
private val FieldBg      = Color(0xFFF1F2F4)
private val FieldBorder  = Color(0xFF4D8FB0)
private val LabelColor   = Color(0xFF1B1B1B)
private val HintColor    = Color(0xFF9AA4AF)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

@Composable
fun LaporkanMasalahScreen(
    onKirim: (email: String, perihal: String, detail: String) -> Unit = { _,_,_ -> }
) {
    var email by remember { mutableStateOf("") }
    var perihal by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {
        // BG dengan header biru melengkung
        Image(
            painter = painterResource(R.drawable.bg_dashboard),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            /* ===== Header (judul + subjudul) ===== */
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Laporkan Masalah",
                color = Color.White,
                fontFamily = PoppinsSemi,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Laporin yaaa kalau ada masalah dengan aplikasi kami!",
                color = Color.White.copy(alpha = 0.9f),
                fontFamily = PoppinsReg,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            /* ===== Panel putih rounded ===== */
            Spacer(Modifier.height(16.dp))
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .imePadding()
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Email
                    LabelText("Email")
                    OutlineField(
                        value = email,
                        onValueChange = { email = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = "nama@email.com"
                    )

                    Spacer(Modifier.height(14.dp))

                    // Perihal
                    LabelText("Perihal")
                    OutlineField(
                        value = perihal,
                        onValueChange = { perihal = it },
                        singleLine = true,
                        placeholder = "Ringkas saja ya…"
                    )

                    Spacer(Modifier.height(14.dp))

                    // Detail
                    LabelText("Detail Pesan")
                    OutlineField(
                        value = detail,
                        onValueChange = { detail = it },
                        singleLine = false,
                        minLines = 6,
                        placeholder = "Ceritakan detail kendalanya…"
                    )

                    Spacer(Modifier.height(26.dp))

                    // Tombol di tengah, lebar tidak penuh (sesuai screenshot)
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { onKirim(email.trim(), perihal.trim(), detail.trim()) },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.88f) // ~88% width biar tampak ada margin kiri/kanan
                                .height(44.dp)
                        ) {
                            Text(
                                "Kirim",
                                color = Color.White,
                                fontFamily = PoppinsSemi,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

/* ===== Components ===== */

@Composable
private fun LabelText(text: String) {
    Text(
        text = text,
        fontFamily = PoppinsReg,
        fontSize = 12.sp,
        color = LabelColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutlineField(
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    minLines: Int = if (singleLine) 1 else 4,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            if (placeholder.isNotEmpty()) {
                Text(
                    text = placeholder,
                    fontFamily = PoppinsReg,
                    fontSize = 12.sp,
                    color = HintColor
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = FieldBorder,
            unfocusedBorderColor = FieldBorder,
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg,
            cursorColor = FieldBorder
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLaporkanMasalah() {
    MaterialTheme { LaporkanMasalahScreen() }
}
