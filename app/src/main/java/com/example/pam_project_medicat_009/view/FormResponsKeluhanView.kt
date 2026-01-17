package com.example.pam_project_medicat_009.view

import android.provider.CalendarContract
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
// Menggunakan ikon standar yang dijamin tidak error
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
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
import com.example.pam_project_medicat_009.viewmodel.ResponsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormResponsKeluhanView(
    keluhanId: Int,
    dokterId: Int,
    navController: NavController
) {
    val vm: ResponsViewModel = viewModel()

    var diagnosis by remember { mutableStateOf("") }
    var anjuran by remember { mutableStateOf("") }
    var rujukan by remember { mutableStateOf("") }

    // Warna unik agar tidak bentrok dengan file lain
    val colorPrimary = Color(0xFF2196F3)
    val colorBackground = Color(0xFFF8FAFC)
    val colorGrey = Color(0xFF64748B)

    LaunchedEffect(vm.success) {
        if (vm.success) {
            navController.popBackStack()
        }
    }

    Scaffold(
        containerColor = colorBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Respons Keluhan Pasien",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        // Menggunakan ikon ArrowBack standar agar tidak Unresolved
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                "Berikan penilaian medis dan anjuran untuk pasien berdasarkan keluhan yang diterima.",
                color = colorGrey,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(32.dp))

            // Field Diagnosis
            Text("Diagnosis Sementara", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = diagnosis,
                onValueChange = { diagnosis = it },
                placeholder = { Text("Tulis diagnosis...", color = colorGrey.copy(alpha = 0.6f)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null, tint = colorPrimary) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorPrimary,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(Modifier.height(20.dp))

            // Field Anjuran
            Text("Anjuran Pola Hidup", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = anjuran,
                onValueChange = { anjuran = it },
                placeholder = { Text("Anjuran istirahat, makan, dll...", color = colorGrey.copy(alpha = 0.6f)) },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null, tint = colorPrimary) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorPrimary,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(Modifier.height(20.dp))

            // Field Rujukan
            Text("Rujukan Rumah Sakit (Opsional)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = rujukan,
                onValueChange = { rujukan = it },
                placeholder = { Text("Nama rumah sakit rujukan...", color = colorGrey.copy(alpha = 0.6f)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                // Menggunakan LocationOn sebagai pengganti HomeRepairService yang error
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = colorPrimary) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorPrimary,
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(Modifier.weight(1f))

            vm.errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(Modifier.height(8.dp))
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = {
                    vm.kirimRespons(keluhanId, dokterId, diagnosis, anjuran, rujukan)
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorPrimary),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    "SIMPAN RESPONS",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                )
            }
        }
    }
}