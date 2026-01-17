package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import com.example.pam_project_medicat_009.viewmodel.KeluhanViewModel

// Menggunakan nama unik agar tidak bentrok (Conflicting declarations)
private val ColorBgForm = Color(0xFFF9F9F7)
private val ColorYellowMedicat = Color(0xFFF0F31D)
private val ColorGrayText = Color(0xFF8E8E8E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormKeluhanView(
    pasienId: Int,
    navController: NavController
) {
    val vm: KeluhanViewModel = viewModel()

    var gejala by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }

    LaunchedEffect(vm.success) {
        if (vm.success) {
            navController.popBackStack()
        }
    }

    Column(Modifier.padding(24.dp)) {

        OutlinedTextField(
            value = gejala,
            onValueChange = { gejala = it },
            label = { Text("Gejala Utama") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(Modifier.height(24.dp))

        vm.errorMessage?.let {
            Text(it, color = Color.Red)
            Spacer(Modifier.height(8.dp))
        }

        Button(
            onClick = { vm.submitKeluhan(pasienId, gejala, deskripsi) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("KIRIM")
        }
    }
}
