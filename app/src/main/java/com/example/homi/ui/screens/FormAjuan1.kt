package com.example.homi.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homi.R

@Composable
fun FormAjuan1() {
    val poppins = FontFamily(Font(R.font.poppins_regular))

    var nama by remember { mutableStateOf("") }
    var jenisAjuan by remember { mutableStateOf("Surat Keterangan") }
    var tanggal by remember { mutableStateOf("") }
    var tempat by remember { mutableStateOf("") }
    var perihal by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C6BA4)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 🔹 Tombol kembali
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.panahkembali),
                contentDescription = "Kembali",
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        // nanti isi aksi navigasi di sini
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Formulir Pengajuan",
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Silahkan mengisi data formulir dibawah ini,\nuntuk melakukan pengajuan layanan:",
            fontFamily = poppins,
            color = Color.White,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 🔹 Bagian form putih
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, Color(0xFF1C6BA4)),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                TextItem(label = "Nama Pelapor", poppins = poppins)
                LineTextField(value = nama, onValueChange = { nama = it }, poppins = poppins)

                TextItem(label = "Jenis Pengajuan", poppins = poppins)
                LineTextField(value = jenisAjuan, onValueChange = { jenisAjuan = it }, poppins = poppins, trailing = true)

                TextItem(label = "Tanggal", poppins = poppins)
                LineTextField(value = tanggal, onValueChange = { tanggal = it }, poppins = poppins)

                TextItem(label = "Tempat", poppins = poppins)
                LineTextField(value = tempat, onValueChange = { tempat = it }, poppins = poppins)

                TextItem(label = "Perihal", poppins = poppins)
                LineTextField(value = perihal, onValueChange = { perihal = it }, poppins = poppins)

                Spacer(modifier = Modifier.height(30.dp))

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

@Composable
fun TextItem(label: String, poppins: FontFamily) {
    Text(
        text = label,
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF1C6BA4),
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    poppins: FontFamily,
    trailing: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = poppins,
            fontSize = 14.sp,
            color = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        trailingIcon = {
            if (trailing) {
                Text(
                    text = ">",
                    fontFamily = poppins,
                    color = Color(0xFF1C6BA4),
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFBBBBBB),
            unfocusedIndicatorColor = Color(0xFFBBBBBB),
            cursorColor = Color.Black
        )   
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFormAjuan1() {
    FormAjuan1()
}
