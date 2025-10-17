package com.example.homi.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

/* ===== TOKENS (konsisten dgn screen lain) ===== */
private val BlueMain      = Color(0xFF2F7FA3)
private val BlueBorder    = Color(0xFF2F7FA3)
private val BlueText      = Color(0xFF2F7FA3)
private val ChipBg        = Color(0xFFE6F3F8)
private val TextDark      = Color(0xFF0E0E0E)
private val LineGray      = Color(0xFFE6E6E6)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

@Composable
fun PembayaranIuranScreen(
    amount: String = "Rp25.000",
    @DrawableRes backIcon: Int = R.drawable.panah,
    @DrawableRes qrIcon: Int = R.drawable.qr_code,  // sediakan bitmap/vec qr atau placeholder
    onBack: (() -> Unit)? = null,
    onRincianClick: (() -> Unit)? = null,
    onRefreshStatus: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueMain)
            .statusBarsPadding()
    ) {
        /* ===== TOP BAR ===== */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBack?.invoke() },
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) { Icon(painterResource(backIcon), contentDescription = "Kembali") }

            Text(
                text = "Pembayaran",
                fontFamily = PoppinsSemi,
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(40.dp))
        }

        Text(
            text = "Silahkan membayar tagihan anda",
            fontFamily = PoppinsReg,
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        /* ===== WHITE CONTAINER ===== */
        Spacer(Modifier.height(12.dp))
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                /* ===== ROW CHIP NOMINAL & RINCIAN ===== */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Chip amount
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(ChipBg)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = amount,
                            fontFamily = PoppinsSemi,
                            fontSize = 14.sp,
                            color = BlueText
                        )
                    }

                    // Rincian (dropdown)
                    TextButton(
                        onClick = { onRincianClick?.invoke() },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text(
                            text = "Rincian ▾",
                            fontFamily = PoppinsReg,
                            fontSize = 12.sp,
                            color = TextDark
                        )
                    }
                }

                Divider(color = LineGray, thickness = 1.dp)

                Spacer(Modifier.height(10.dp))

                /* ===== QR AREA ===== */
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // ikon kecil di atas tulisan QRIS (opsional)
                    Image(
                        painter = painterResource(id = R.drawable.ic_qr), // jika tak ada, ganti ke qrIcon atau hapus
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "QRIS",
                        fontFamily = PoppinsSemi,
                        fontSize = 14.sp,
                        color = TextDark
                    )
                    Spacer(Modifier.height(8.dp))

                    // QR besar
                    Image(
                        painter = painterResource(qrIcon),
                        contentDescription = "QR Pembayaran",
                        modifier = Modifier
                            .size(220.dp)
                            .padding(2.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(Modifier.height(18.dp))

                /* ===== REFRESH STATUS ===== */
                OutlinedButton(
                    onClick = { onRefreshStatus?.invoke() },
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, BlueBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                ) {
                    Text(
                        text = "Refresh Status Pembayaran",
                        fontFamily = PoppinsSemi,
                        fontSize = 14.sp,
                        color = BlueText
                    )
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

/* ===== PREVIEW ===== */
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewPembayaran() {
    MaterialTheme {
        // ganti qrIcon dgn drawable qr kamu (mis. R.drawable.qris_png)
        PembayaranIuranScreen(qrIcon = R.drawable.qr_code)
    }
}
