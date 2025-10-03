package com.example.homi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarScreen(
    onRegisterClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {}
) {
    val poppins = FontFamily(Font(R.font.poppins_semibold))

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.daftar),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 333.dp)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Daftar",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xFF256D85),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nama Pengguna", fontFamily = poppins) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    focusedBorderColor = Color(0xFF256D85),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontFamily = poppins) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    focusedBorderColor = Color(0xFF256D85),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Kata Sandi", fontFamily = poppins) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    focusedBorderColor = Color(0xFF256D85),
                    unfocusedBorderColor = Color.Gray
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = R.drawable.hide
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Gray
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Konfirmasi Kata Sandi", fontFamily = poppins) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    focusedBorderColor = Color(0xFF256D85),
                    unfocusedBorderColor = Color.Gray
                ),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = R.drawable.hide
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Gray
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Button Konfirmasi
            Button(
                onClick = onRegisterClicked,
                modifier = Modifier
                    .width(285.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8A477)
                )
            ) {
                Text(
                    text = "Konfirmasi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login redirect text
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sudah punya akun?",
                    fontSize = 10.sp,
                    fontFamily = poppins,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Masuk",
                    fontSize = 10.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDaftarScreen() {
    MaterialTheme {
        DaftarScreen()
    }
}