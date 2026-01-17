package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam_project_medicat_009.viewmodel.LoginViewModel

// Definisi Warna sesuai Gambar
val MedicatLightBg = Color(0xFFF8FAFC)
val MedicatGreyText = Color(0xFF64748B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController) {

    val vm: LoginViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(vm.loginSuccess) {
        if (vm.loginSuccess) {
            when (vm.role) {
                "pasien" -> {
                    navController.navigate("home_pasien/${vm.userId}") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                "dokter" -> {
                    navController.navigate("home_dokter/${vm.userId}") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MedicatLightBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(48.dp))

            // Title & Subtitle
            Text(
                "SELAMAT DATANG\nDI MEDICAT",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                color = Color.Black
            )
            Text(
                "Manage your health with ease",
                color = MedicatGreyText,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(Modifier.height(32.dp))

            // Logo Box
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MedicatDarkBlue, shape = RoundedCornerShape(30.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // Ganti dengan icon tas medis jika ada
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(95.dp)
                )
            }

            Spacer(Modifier.height(48.dp))

            // Input Email
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email Anda") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = MedicatGreyText) },
            )

            Spacer(Modifier.height(16.dp))

            // Input Password
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MedicatGreyText) },
            )


            Spacer(Modifier.height(32.dp))

            vm.errorMessage?.let {
                Text(it, color = Color.Red, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
            }

            // Tombol Log In
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = { vm.login(email, password) },
                colors = ButtonDefaults.buttonColors(containerColor = MedicatDarkBlue),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("LOG IN", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Footer: Register
            TextButton(
                onClick = { navController.navigate("register") }
            ) {
                Text(
                    buildAnnotatedString {
                        append("Belum Memiliki Akun? ")
                        withStyle(style = SpanStyle(color = MedicatDarkBlue, fontWeight = FontWeight.Bold)) {
                            append("silahkan daftar")
                        }
                    },
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}