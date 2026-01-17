package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_project_medicat_009.viewmodel.PasienViewModel

@Composable
fun ProfilPasienView(pasienId: Int) {

    val vm: PasienViewModel = viewModel()

    LaunchedEffect(Unit) {
        vm.loadPasien(pasienId)
    }

    vm.pasien?.let { pasien ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                "Profil Pasien",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Nama       : ${pasien.nama}")
            Text("Email      : ${pasien.email}")
            Text("No Telp    : ${pasien.no_telp}")
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}