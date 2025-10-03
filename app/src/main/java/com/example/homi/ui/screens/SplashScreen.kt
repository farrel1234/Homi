package com.example.homi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Color


@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {
    // Font family dari res/font/la_belle_aurore.ttf
    val laBelleAurore = FontFamily(
        Font(R.font.la_belle_aurore) // sama persis dengan nama file
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.homi_logo),
                    contentDescription = "Logo Homi",
                    modifier = Modifier.size(220.dp)
                )

                Text(
                    text = "Homi",
                    fontSize = 45.sp,
                    fontFamily = laBelleAurore,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF7C0A2),
                    modifier = Modifier.offset(y = (-40).dp)
                )
            }
        }
    }

    // Timer 2 detik sebelum pindah ke layar lain
    LaunchedEffect(Unit) {
        delay(2000)
        onSplashFinished()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSplashScreen() {
    MaterialTheme {
        SplashScreen()
    }
}
