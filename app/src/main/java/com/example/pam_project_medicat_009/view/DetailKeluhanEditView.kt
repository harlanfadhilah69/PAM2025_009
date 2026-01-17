package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam_project_medicat_009.viewmodel.KeluhanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKeluhanEditView(
    keluhanId: Int,
    navController: NavController
) {
    val vm: KeluhanViewModel = viewModel()
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(keluhanId) {
        vm.loadDetailKeluhan(keluhanId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Keluhan") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        when {
            vm.isLoading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            vm.selectedKeluhan != null -> {
                val k = vm.selectedKeluhan!!

                var gejala by remember { mutableStateOf(k.gejalaUtama) }
                var deskripsi by remember { mutableStateOf(k.deskripsi ?: "") }

                Column(
                    Modifier
                        .padding(padding)
                        .padding(24.dp)
                ) {

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

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            vm.updateKeluhan(
                                k.idKeluhan,
                                gejala,
                                deskripsi
                            ) {
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Text("UPDATE")
                    }

                    Spacer(Modifier.height(12.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        onClick = { showDeleteDialog = true }
                    ) {
                        Text("HAPUS", color = Color.White)
                    }
                }

                if (showDeleteDialog) {
                    AlertDialog(
                        onDismissRequest = { showDeleteDialog = false },
                        title = { Text("Hapus Keluhan") },
                        text = { Text("Yakin ingin menghapus keluhan ini?") },
                        confirmButton = {
                            TextButton(onClick = {
                                vm.deleteKeluhan(k.idKeluhan) {
                                    navController.popBackStack()
                                }
                            }) {
                                Text("HAPUS", color = Color.Red)
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDeleteDialog = false }) {
                                Text("BATAL")
                            }
                        }
                    )
                }
            }

            else -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text("Data tidak ditemukan")
                }
            }
        }
    }
}

