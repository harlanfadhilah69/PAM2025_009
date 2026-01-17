package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam_project_medicat_009.viewmodel.RegisterViewModel

// CATATAN: Jika error "Conflicting declarations", hapus baris warna di bawah ini
// karena sudah ada di LoginView.kt (dalam satu package yang sama)
private val RegMedicatDarkBlue = Color(0xFF1D3A8A)
private val RegMedicatLightBg = Color(0xFFF8FAFC)
private val RegMedicatGreyText = Color(0xFF64748B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(navController: NavController) {

    val vm: RegisterViewModel = viewModel()

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var noTelp by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("pasien") }
    var expanded by remember { mutableStateOf(false) }

    var spesialisasi by remember { mutableStateOf("") }
    var nomorIzin by remember { mutableStateOf("") }

    LaunchedEffect(vm.registerSuccess) {
        if (vm.registerSuccess) {
            navController.popBackStack()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = RegMedicatLightBg
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(Modifier.height(24.dp))


                Spacer(Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(RegMedicatDarkBlue.copy(alpha = 0.1f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Menggunakan Person agar tidak butuh library extended icons
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = RegMedicatDarkBlue,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    "Silahkan Daftar",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 26.sp,
                    color = Color.Black
                )
                Text(
                    "Buat akun baru untuk memulai",
                    color = RegMedicatGreyText,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(32.dp))
            }

            item {
                CustomRegisterField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = "Nama Lengkap",
                    placeholder = "Masukkan nama lengkap",
                    icon = Icons.Default.AccountBox
                )
                Spacer(Modifier.height(16.dp))

                CustomRegisterField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    placeholder = "contoh@email.com",
                    icon = Icons.Default.Email
                )
                Spacer(Modifier.height(16.dp))

                CustomRegisterField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "••••••••",
                    icon = Icons.Default.Lock
                )
                Spacer(Modifier.height(16.dp))

                CustomRegisterField(
                    value = noTelp,
                    onValueChange = { noTelp = it },
                    label = "Nomor Telepon",
                    placeholder = "0812...",
                    icon = Icons.Default.Phone
                )
                Spacer(Modifier.height(16.dp))

                Text(
                    "Peran",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = role.replaceFirstChar { it.uppercase() },
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        leadingIcon = { Icon(Icons.Default.Face, null, tint = Color.DarkGray) },
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,   // Warna teks saat fokus
                            unfocusedTextColor = Color.Black, // Warna teks saat tidak fokus
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Pasien") },
                            onClick = { role = "pasien"; expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Dokter") },
                            onClick = { role = "dokter"; expanded = false }
                        )
                    }
                }
            }

            if (role == "dokter") {
                item {
                    Spacer(Modifier.height(16.dp))
                    CustomRegisterField(
                        value = spesialisasi,
                        onValueChange = { spesialisasi = it },
                        label = "Spesialisasi",
                        placeholder = "Contoh: Jantung",
                        icon = Icons.Default.Info
                    )
                    Spacer(Modifier.height(16.dp))
                    CustomRegisterField(
                        value = nomorIzin,
                        onValueChange = { nomorIzin = it },
                        label = "Nomor Izin Praktek",
                        placeholder = "Masukkan nomor izin",
                        icon = Icons.Default.Build
                    )
                }
            }

            item {
                Spacer(Modifier.height(32.dp))

                vm.errorMessage?.let {
                    Text(it, color = Color.Red, fontSize = 12.sp)
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    onClick = {
                        if (role == "pasien") {
                            vm.registerPasien(nama, email, password, noTelp)
                        } else {
                            vm.registerDokter(nama, email, password, spesialisasi, nomorIzin)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MedicatDarkBlue),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("DAFTAR", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                }

                Spacer(Modifier.height(24.dp))

                TextButton(onClick = { navController.popBackStack() }) {
                    Text(
                        text = "Sudah punya akun? Masuk sekarang",
                        color = RegMedicatGreyText,
                        fontSize = 14.sp
                    )
                }
                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomRegisterField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column {
        Text(
            label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black, // Pastikan label juga hitam
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = { Icon(icon, null, tint = Color.DarkGray) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                // Mengubah warna teks saat diketik menjadi hitam
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

                // Menjaga background tetap putih
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,

                // Menghilangkan garis bawah
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}