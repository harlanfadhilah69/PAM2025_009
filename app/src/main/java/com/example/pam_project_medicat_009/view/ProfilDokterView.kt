package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam_project_medicat_009.viewmodel.DokterViewModel
import com.example.pam_project_medicat_009.modeldata.dokter.Dokter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDokterView(
    dokterId: Int,
    navController: NavController
) {
    val vm: DokterViewModel = viewModel()

    LaunchedEffect(dokterId) {
        vm.loadProfilDokter(dokterId)
    }

    val dokter = vm.dokter.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Dokter") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        dokter?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp)
            ) {

                Text(
                    "Profil Dokter",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Nama                  : ${it.nama}")
                Text("Email                 : ${it.email}")
                Text("Spesialisasi           : ${it.spesialisasi}")
                Text("Nomor Izin Praktek     : ${it.nomorIzinPraktek}")

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo(0)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Logout", color = Color.White)
                }
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
