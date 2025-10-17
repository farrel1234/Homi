package com.example.homi.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

/* ===== TOKENS ===== */
private val BlueMain    = Color(0xFF2F7FA3)
private val BlueBorder  = Color(0xFF2F7FA3)
private val BlueText    = Color(0xFF2F7FA3)
private val ChipBg      = Color(0xFFE6F3F8)
private val TextDark    = Color(0xFF0E0E0E)
private val LineGray    = Color(0xFFE6E6E6)
private val HintGray    = Color(0xFF9AA4AF)

private val PoppinsSemi = FontFamily(Font(R.font.poppins_semibold))
private val PoppinsReg  = FontFamily(Font(R.font.poppins_regular))

@Composable
fun PembayaranIuranScreen(
    amount: String = "Rp25.000",
    bulan: String = "Agustus 2025",
    transaksiId: String = "IPL-123456789",
    @DrawableRes backIcon: Int = R.drawable.panah,
    @DrawableRes qrIcon: Int = R.drawable.qr_code,
    onBack: (() -> Unit)? = null,
) {
    var rincianExpanded by remember { mutableStateOf(true) }

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
            text = "Segera membayar tagihan iuran yang tersedia",
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
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                /* ===== ROW: NOMINAL CHIP + RINCIAN TOGGLE ===== */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nominal
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

                    // Toggle Rincian
                    TextButton(
                        onClick = { rincianExpanded = !rincianExpanded },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text(
                            text = if (rincianExpanded) "Rincian ▴" else "Rincian ▾",
                            fontFamily = PoppinsReg,
                            fontSize = 12.sp,
                            color = TextDark
                        )
                    }
                }

                Divider(color = LineGray, thickness = 1.dp)

                /* ===== RINCIAN (expand/collapse) ===== */
                AnimatedVisibility(
                    visible = rincianExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 6.dp)
                    ) {
                        // garis dengan judul di tengah (seperti tab "Rincian Pembayaran")
                        Box(Modifier.fillMaxWidth()) {
                            Divider(color = LineGray, thickness = 1.dp, modifier = Modifier.align(Alignment.Center))
                            Text(
                                text = "Rincian Pembayaran",
                                fontFamily = PoppinsSemi,
                                fontSize = 12.sp,
                                color = HintGray,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .background(Color.White)
                                    .padding(horizontal = 10.dp)
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        RincianRow("Total Pembayaran", amount, highlightRight = true)
                        Divider(color = LineGray, thickness = 1.dp)
                        RincianRow("Bulan", bulan)
                        Divider(color = LineGray, thickness = 1.dp)
                        RincianRow("Transaksi ID", transaksiId)
                        Divider(color = LineGray, thickness = 1.dp)
                    }
                }

                Spacer(Modifier.height(10.dp))

                /* ===== QR AREA ===== */
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // ikon kecil di atas tulisan QRIS (opsional)
                    Image(
                        painter = painterResource(id = R.drawable.ic_qr),
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

                    Image(
                        painter = painterResource(qrIcon),
                        contentDescription = "QR Pembayaran",
                        modifier = Modifier
                            .size(220.dp)
                            .padding(2.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(Modifier.height(16.dp))
                // (Sesuai permintaan) Tombol refresh DIHAPUS
            }
        }
    }
}

/* ====== Subcomponents ====== */

@Composable
private fun RincianRow(
    left: String,
    right: String,
    highlightRight: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = left,
            fontFamily = PoppinsReg,
            fontSize = 12.sp,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = right,
            fontFamily = if (highlightRight) PoppinsSemi else PoppinsReg,
            fontSize = 12.sp,
            color = if (highlightRight) BlueText else Color(0xFF475569),
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

/* ===== PREVIEW ===== */
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewPembayaran() {
    MaterialTheme {
        PembayaranIuranScreen(qrIcon = R.drawable.qr_code)
    }
}
